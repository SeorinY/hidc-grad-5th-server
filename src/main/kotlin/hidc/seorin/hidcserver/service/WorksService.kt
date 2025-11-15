package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.DesignersDomain
import hidc.seorin.hidcserver.domain.ProfessorDomain
import hidc.seorin.hidcserver.domain.WorksCategoryDomain
import hidc.seorin.hidcserver.domain.WorksDomain
import hidc.seorin.hidcserver.domain.WorksFileDomain
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
    fun findAll(): List<WorksDomain> {
        return worksRepository
            .findAll()
            .map {
                val works = WorksDomain.from(it)
                works.designers = it
                    .designers
                    .map { designers -> DesignersDomain.from(designers) }
                works
            }
    }

    fun findById(id: Long): WorksDomain? {
        return worksRepository
            .findById(id)
            .orElse(null)
            ?.let {
                val works = WorksDomain.from(it)
                works.leadDesigner = it.leadDesigner?.let { DesignersDomain.from(it) }
                works.designers = it
                    .designers
                    .map { designers -> DesignersDomain.from(designers) }
                works.categories = it
                    .categories
                    .map { category -> WorksCategoryDomain.from(category) }
                works.professor = it.professor?.let { ProfessorDomain.from(it) }
                works.worksFiles = it.worksFiles.map { WorksFileDomain.from(it) }
                works
            }
    }

    fun findByCategoryId(categoryId: Int?): List<WorksDomain> {
        val works = categoryId?.let {
            worksCategoryRepository
                .findById(categoryId)
                .orElse(null)
                ?.works
                ?: emptyList()
        } ?: worksRepository.findAll()
        
        return works.map {
            val works = WorksDomain.from(it)
            works.designers = it
                .designers
                .map { designers -> DesignersDomain.from(designers) }
            works
        }
    }

    @Transactional
    fun create(request: CreateWorksRequest): WorksDomain {
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
        return WorksDomain.from(worksRepository.save(works))
    }

    @Transactional
    fun update(id: Long, request: UpdateWorksRequest): WorksDomain? {
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
        return WorksDomain.from(worksRepository.save(updated))
    }

    @Transactional
    fun delete(id: Long): Boolean {
        if (!worksRepository.existsById(id)) return false
        worksRepository.deleteById(id)
        return true
    }
}

