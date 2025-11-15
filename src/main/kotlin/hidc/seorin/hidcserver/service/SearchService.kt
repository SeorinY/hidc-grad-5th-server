package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.DesignersDomain
import hidc.seorin.hidcserver.domain.ProfessorDomain
import hidc.seorin.hidcserver.domain.WorksCategoryDomain
import hidc.seorin.hidcserver.domain.WorksDomain
import hidc.seorin.hidcserver.dto.SearchResult
import hidc.seorin.hidcserver.repository.DesignersRepository
import hidc.seorin.hidcserver.repository.ProfessorRepository
import hidc.seorin.hidcserver.repository.WorksRepository
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val professorRepository: ProfessorRepository,
    private val designersRepository: DesignersRepository,
    private val worksRepository: WorksRepository
) {
    fun search(keyword: String): SearchResult {
        val professors = professorRepository.findByNameContaining(keyword)
        if (professors.isNotEmpty()) {
            return SearchResult(
                professors = professors.map {professor ->
                    val professorDomain = ProfessorDomain.from(professor)
                    professorDomain.works = professor.works.map { WorksDomain.from(it) }
                    professorDomain
                },
                resultType = "PROFESSOR"
            )
        }
        val designers = designersRepository.findByNameContaining(keyword)
        if (designers.isNotEmpty()) {
            return SearchResult(
                designers = designers.map { designer ->
                    val designersDomain = DesignersDomain.from(designer)
                    designersDomain.works = designer.works?.let { WorksDomain.from(it) }
                    designersDomain
                },
                resultType = "DESIGNER"
            )
        }

        val works = worksRepository.findByNameContaining(keyword)
        if (works.isNotEmpty()) {
            return SearchResult(
                works = works.map { works ->
                    val worksDomain = WorksDomain.from(works)
                    worksDomain.designers = works.designers.map { DesignersDomain.from(it) }
                    worksDomain.categories = works.categories.map { WorksCategoryDomain.from(it) }
                    worksDomain
                },
                resultType = "WORKS"
            )
        }

        return SearchResult(resultType = "EMPTY")
    }
}

