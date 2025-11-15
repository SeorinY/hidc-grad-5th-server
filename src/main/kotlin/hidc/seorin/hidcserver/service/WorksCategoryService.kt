package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.WorksCategoryDomain
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
    fun findAll(): List<WorksCategoryDomain> {
        return worksCategoryRepository.findAll().map { WorksCategoryDomain.from(it) }
    }

    fun findById(id: Int): WorksCategoryDomain? {
        return worksCategoryRepository.findById(id).orElse(null)?.let { WorksCategoryDomain.from(it) }
    }

    @Transactional
    fun create(request: CreateWorksCategoryRequest): WorksCategoryDomain {
        val category = WorksCategory(name = request.name)
        return WorksCategoryDomain.from(worksCategoryRepository.save(category))
    }

    @Transactional
    fun update(id: Int, request: UpdateWorksCategoryRequest): WorksCategoryDomain? {
        val category = worksCategoryRepository.findById(id).orElse(null) ?: return null
        val updated = category.copy(name = request.name)
        return WorksCategoryDomain.from(worksCategoryRepository.save(updated))
    }

    @Transactional
    fun delete(id: Int): Boolean {
        if (!worksCategoryRepository.existsById(id)) return false
        worksCategoryRepository.deleteById(id)
        return true
    }
}

