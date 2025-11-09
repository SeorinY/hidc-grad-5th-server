package hidc.seorin.hidcserver.service

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
                professors = professors,
                resultType = "PROFESSOR"
            )
        }
        val designers = designersRepository.findByNameContaining(keyword)
        if (designers.isNotEmpty()) {
            return SearchResult(
                designers = designers,
                resultType = "DESIGNER"
            )
        }

        val works = worksRepository.findByNameContaining(keyword)
        if (works.isNotEmpty()) {
            return SearchResult(
                works = works,
                resultType = "WORKS"
            )
        }

        return SearchResult(resultType = "EMPTY")
    }
}

