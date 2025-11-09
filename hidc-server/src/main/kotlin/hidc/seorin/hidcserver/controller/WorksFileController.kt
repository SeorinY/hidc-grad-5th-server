package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.WorksFile
import hidc.seorin.hidcserver.service.WorksFileService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/works-files")
class WorksFileController(
    private val worksFileService: WorksFileService
) {
    @GetMapping
    fun findAll(): List<WorksFile> {
        return worksFileService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<WorksFile> {
        val file = worksFileService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(file)
    }
}

