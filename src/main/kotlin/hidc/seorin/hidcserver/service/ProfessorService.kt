package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.Professor
import hidc.seorin.hidcserver.repository.ProfessorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProfessorService(
    private val professorRepository: ProfessorRepository
) {
    fun findAll(): List<Professor> {
        return professorRepository.findAll()
    }

    fun findById(id: Int): Professor? {
        return professorRepository.findById(id).orElse(null)
    }
}

