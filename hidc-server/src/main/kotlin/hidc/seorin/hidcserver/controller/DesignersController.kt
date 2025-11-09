package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.service.DesignersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/designers")
class DesignersController(
    private val designersService: DesignersService
) {
    @GetMapping
    fun findAll(): List<Designers> {
        return designersService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Designers> {
        val designers = designersService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(designers)
    }
}

