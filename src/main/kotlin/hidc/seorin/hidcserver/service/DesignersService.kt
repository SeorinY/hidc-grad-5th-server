package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.SortType
import hidc.seorin.hidcserver.dto.CreateDesignersRequest
import hidc.seorin.hidcserver.dto.UpdateDesignersRequest
import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.repository.DesignersRepository
import hidc.seorin.hidcserver.repository.WorksRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DesignersService(
    private val designersRepository: DesignersRepository,
    private val worksRepository: WorksRepository
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

    @Transactional
    fun create(request: CreateDesignersRequest): Designers {
        val works = request.worksId?.let { worksRepository.findById(it).orElse(null) }
        
        val designer = Designers(
            name = request.name,
            enName = request.enName,
            imageUrl = request.imageUrl,
            email = request.email,
            profileUrl = request.profileUrl,
            linkedinUrl = request.linkedinUrl,
            instagramUrl = request.instagramUrl,
            behanceUrl = request.behanceUrl,
            works = works
        )
        return designersRepository.save(designer)
    }

    @Transactional
    fun update(id: Long, request: UpdateDesignersRequest): Designers? {
        val designer = designersRepository.findById(id).orElse(null) ?: return null
        val works = request.worksId?.let { worksRepository.findById(it).orElse(null) }
        
        val updated = designer.copy(
            name = request.name,
            enName = request.enName,
            imageUrl = request.imageUrl,
            email = request.email,
            profileUrl = request.profileUrl,
            linkedinUrl = request.linkedinUrl,
            instagramUrl = request.instagramUrl,
            behanceUrl = request.behanceUrl,
            works = works
        )
        return designersRepository.save(updated)
    }

    @Transactional
    fun delete(id: Long): Boolean {
        if (!designersRepository.existsById(id)) return false
        designersRepository.deleteById(id)
        return true
    }
}

