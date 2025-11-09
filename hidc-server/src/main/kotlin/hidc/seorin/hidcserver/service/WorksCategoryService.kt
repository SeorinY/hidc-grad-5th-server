package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.WorksCategory
import hidc.seorin.hidcserver.repository.WorksCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksCategoryService(
    private val worksCategoryRepository: WorksCategoryRepository
) {
    fun findAll(): List<WorksCategory> {
        return worksCategoryRepository.findAll()
    }

    fun findById(id: Int): WorksCategory? {
        return worksCategoryRepository.findById(id).orElse(null)
    }
}

