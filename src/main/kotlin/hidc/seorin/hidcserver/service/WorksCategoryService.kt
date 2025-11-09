package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.dto.CreateWorksCategoryRequest
import hidc.seorin.hidcserver.dto.UpdateWorksCategoryRequest
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

    @Transactional
    fun create(request: CreateWorksCategoryRequest): WorksCategory {
        val category = WorksCategory(name = request.name)
        return worksCategoryRepository.save(category)
    }

    @Transactional
    fun update(id: Int, request: UpdateWorksCategoryRequest): WorksCategory? {
        val category = worksCategoryRepository.findById(id).orElse(null) ?: return null
        val updated = category.copy(name = request.name)
        return worksCategoryRepository.save(updated)
    }

    @Transactional
    fun delete(id: Int): Boolean {
        if (!worksCategoryRepository.existsById(id)) return false
        worksCategoryRepository.deleteById(id)
        return true
    }
}

