package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.Works
import hidc.seorin.hidcserver.repository.WorksCategoryRepository
import hidc.seorin.hidcserver.repository.WorksRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksService(
    private val worksRepository: WorksRepository,
    private val worksCategoryRepository: WorksCategoryRepository,
) {
    fun findAll(): List<Works> {
        return worksRepository.findAll()
    }

    fun findById(id: Long): Works? {
        return worksRepository.findById(id).orElse(null)
    }

    fun findByCategoryId(categoryId: Int?): List<Works> {
        return categoryId?.let {
            worksCategoryRepository
                .findById(categoryId)
                .orElse(null)
                ?.works
                ?: emptyList()
        } ?: worksRepository.findAll()
    }
}

