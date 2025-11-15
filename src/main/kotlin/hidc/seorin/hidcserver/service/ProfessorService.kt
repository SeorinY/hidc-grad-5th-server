package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.domain.ProfessorDomain
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
    fun findAll(): List<ProfessorDomain> {
        return professorRepository.findAll().map { ProfessorDomain.from(it) }
    }

    fun findById(id: Int): ProfessorDomain? {
        return professorRepository.findById(id).orElse(null)?.let { ProfessorDomain.from(it) }
    }

    @Transactional
    fun create(request: CreateProfessorRequest): ProfessorDomain {
        val professor = Professor(
            name = request.name,
            className = request.className
        )
        return ProfessorDomain.from(professorRepository.save(professor))
    }

    @Transactional
    fun update(id: Int, request: UpdateProfessorRequest): ProfessorDomain? {
        val professor = professorRepository.findById(id).orElse(null) ?: return null
        val updated = professor.copy(
            name = request.name,
            className = request.className
        )
        return ProfessorDomain.from(professorRepository.save(updated))
    }

    @Transactional
    fun delete(id: Int): Boolean {
        if (!professorRepository.existsById(id)) return false
        professorRepository.deleteById(id)
        return true
    }
}

