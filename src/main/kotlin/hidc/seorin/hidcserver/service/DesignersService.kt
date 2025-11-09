package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.SortType
import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.repository.DesignersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DesignersService(
    private val designersRepository: DesignersRepository
) {
    fun findAll(sortType: SortType): List<Designers> {
        val designers = designersRepository.findAll()
        
        return when (sortType) {
            SortType.ASC -> designers.sortedBy { it.name }
            SortType.DESC -> designers.sortedByDescending { it.name }
            SortType.RANDOM -> designers.shuffled()
        }
    }

    fun findById(id: Long): Designers? {
        return designersRepository.findById(id).orElse(null)
    }
}

