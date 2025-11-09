package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.Professor
import hidc.seorin.hidcserver.service.ProfessorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/professors")
class ProfessorController(
    private val professorService: ProfessorService
) {
    @GetMapping
    fun findAll(): List<Professor> {
        return professorService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Professor> {
        val professor = professorService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(professor)
    }
}

