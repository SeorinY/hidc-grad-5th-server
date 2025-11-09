package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.Works
import hidc.seorin.hidcserver.service.WorksService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/works")
class WorksController(
    private val worksService: WorksService
) {
    @GetMapping
    fun findAll(): List<Works> {
        return worksService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Works> {
        return worksService.findById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/categories/{id}")
    fun findByCategoryId(@PathVariable categoryId: Int): ResponseEntity<List<Works>> {
        return ResponseEntity.ok(
            worksService.findByCategoryId(categoryId)
        )
    }
}
