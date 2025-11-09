package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.dto.CreateProfessorRequest
import hidc.seorin.hidcserver.dto.UpdateProfessorRequest
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

    @Transactional
    fun create(request: CreateProfessorRequest): Professor {
        val professor = Professor(name = request.name)
        return professorRepository.save(professor)
    }

    @Transactional
    fun update(id: Int, request: UpdateProfessorRequest): Professor? {
        val professor = professorRepository.findById(id).orElse(null) ?: return null
        val updated = professor.copy(name = request.name)
        return professorRepository.save(updated)
    }

    @Transactional
    fun delete(id: Int): Boolean {
        if (!professorRepository.existsById(id)) return false
        professorRepository.deleteById(id)
        return true
    }
}

