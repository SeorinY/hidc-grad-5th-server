package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.dto.CreateWorksRequest
import hidc.seorin.hidcserver.dto.UpdateWorksRequest
import hidc.seorin.hidcserver.entity.Works
import hidc.seorin.hidcserver.repository.DesignersRepository
import hidc.seorin.hidcserver.repository.ProfessorRepository
import hidc.seorin.hidcserver.repository.WorksCategoryRepository
import hidc.seorin.hidcserver.repository.WorksRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class WorksService(
    private val worksRepository: WorksRepository,
    private val worksCategoryRepository: WorksCategoryRepository,
    private val professorRepository: ProfessorRepository,
    private val designersRepository: DesignersRepository
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

    @Transactional
    fun create(request: CreateWorksRequest): Works {
        val professor = request.professorId?.let { professorRepository.findById(it).orElse(null) }
        val leadDesigner = request.leadDesignerId?.let { designersRepository.findById(it).orElse(null) }
        val categories = request.categoryIds?.mapNotNull { 
            worksCategoryRepository.findById(it).orElse(null) 
        } ?: emptyList()
        
        val works = Works(
            thumbnailImageUrl = request.thumbnailImageUrl,
            imageUrl = request.imageUrl,
            name = request.name,
            description = request.description,
            enDescription = request.enDescription,
            professor = professor,
            leadDesigner = leadDesigner,
            categories = categories
        )
        return worksRepository.save(works)
    }

    @Transactional
    fun update(id: Long, request: UpdateWorksRequest): Works? {
        val works = worksRepository.findById(id).orElse(null) ?: return null
        val professor = request.professorId?.let { professorRepository.findById(it).orElse(null) }
        val leadDesigner = request.leadDesignerId?.let { designersRepository.findById(it).orElse(null) }
        val categories = request.categoryIds?.mapNotNull { 
            worksCategoryRepository.findById(it).orElse(null) 
        } ?: emptyList()
        
        val updated = works.copy(
            thumbnailImageUrl = request.thumbnailImageUrl,
            imageUrl = request.imageUrl,
            name = request.name,
            description = request.description,
            enDescription = request.enDescription,
            professor = professor,
            leadDesigner = leadDesigner,
            categories = categories
        )
        return worksRepository.save(updated)
    }

    @Transactional
    fun delete(id: Long): Boolean {
        if (!worksRepository.existsById(id)) return false
        worksRepository.deleteById(id)
        return true
    }
}

