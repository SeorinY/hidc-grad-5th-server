package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.repository.DesignersRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DesignersService(
    private val designersRepository: DesignersRepository
) {
    fun findAll(): List<Designers> {
        return designersRepository.findAll()
    }

    fun findById(id: Long): Designers? {
        return designersRepository.findById(id).orElse(null)
    }
}

