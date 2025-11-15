package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.WorksFileDomain
import hidc.seorin.hidcserver.dto.CreateWorksFileRequest
import hidc.seorin.hidcserver.dto.UpdateWorksFileRequest
import hidc.seorin.hidcserver.entity.WorksFile
import hidc.seorin.hidcserver.repository.WorksFileRepository
import hidc.seorin.hidcserver.repository.WorksRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksFileService(
    private val worksFileRepository: WorksFileRepository,
    private val worksRepository: WorksRepository
) {
    fun findAll(): List<WorksFileDomain> {
        return worksFileRepository.findAll().map { WorksFileDomain.from(it) }
    }

    fun findById(id: Long): WorksFileDomain? {
        return worksFileRepository.findById(id).orElse(null)?.let { WorksFileDomain.from(it) }
    }

    @Transactional
    fun create(request: CreateWorksFileRequest): WorksFileDomain {
        val works = worksRepository.findById(request.worksId).orElseThrow {
            IllegalArgumentException("Works not found with id: ${request.worksId}")
        }
        
        val worksFile = WorksFile(
            fileUrl = request.fileUrl,
            fileType = request.fileType,
            seq = request.seq,
            works = works
        )
        return WorksFileDomain.from(worksFileRepository.save(worksFile))
    }

    @Transactional
    fun update(id: Long, request: UpdateWorksFileRequest): WorksFileDomain? {
        val worksFile = worksFileRepository.findById(id).orElse(null) ?: return null
        val works = worksRepository.findById(request.worksId).orElseThrow {
            IllegalArgumentException("Works not found with id: ${request.worksId}")
        }
        
        val updated = worksFile.copy(
            fileUrl = request.fileUrl,
            fileType = request.fileType,
            seq = request.seq,
            works = works
        )
        return WorksFileDomain.from(worksFileRepository.save(updated))
    }

    @Transactional
    fun delete(id: Long): Boolean {
        if (!worksFileRepository.existsById(id)) return false
        worksFileRepository.deleteById(id)
        return true
    }
}

