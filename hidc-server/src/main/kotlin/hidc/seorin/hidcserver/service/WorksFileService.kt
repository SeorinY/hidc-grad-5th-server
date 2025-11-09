package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.WorksFile
import hidc.seorin.hidcserver.repository.WorksFileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksFileService(
    private val worksFileRepository: WorksFileRepository
) {
    fun findAll(): List<WorksFile> {
        return worksFileRepository.findAll()
    }

    fun findById(id: Long): WorksFile? {
        return worksFileRepository.findById(id).orElse(null)
    }
}

