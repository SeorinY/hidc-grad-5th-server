package hidc.seorin.hidcserver.service

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
    fun findAll(): List<WorksFile> {
        return worksFileRepository.findAll()
    }

    fun findById(id: Long): WorksFile? {
        return worksFileRepository.findById(id).orElse(null)
    }

    @Transactional
    fun create(request: CreateWorksFileRequest): WorksFile {
        val works = worksRepository.findById(request.worksId).orElseThrow {
            IllegalArgumentException("Works not found with id: ${request.worksId}")
        }
        
        val worksFile = WorksFile(
            fileUrl = request.fileUrl,
            seq = request.seq,
            works = works
        )
        return worksFileRepository.save(worksFile)
    }

    @Transactional
    fun update(id: Long, request: UpdateWorksFileRequest): WorksFile? {
        val worksFile = worksFileRepository.findById(id).orElse(null) ?: return null
        val works = worksRepository.findById(request.worksId).orElseThrow {
            IllegalArgumentException("Works not found with id: ${request.worksId}")
        }
        
        val updated = worksFile.copy(
            fileUrl = request.fileUrl,
            seq = request.seq,
            works = works
        )
        return worksFileRepository.save(updated)
    }

    @Transactional
    fun delete(id: Long): Boolean {
        if (!worksFileRepository.existsById(id)) return false
        worksFileRepository.deleteById(id)
        return true
    }
}

