package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.WorksCategory
import hidc.seorin.hidcserver.service.WorksCategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class WorksCategoryController(
    private val worksCategoryService: WorksCategoryService
) {
    @GetMapping
    fun findAll(): List<WorksCategory> {
        return worksCategoryService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<WorksCategory> {
        val category = worksCategoryService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(category)
    }
}

