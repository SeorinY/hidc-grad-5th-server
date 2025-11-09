package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.dto.CreateWorksCategoryRequest
import hidc.seorin.hidcserver.dto.UpdateWorksCategoryRequest
import hidc.seorin.hidcserver.entity.WorksCategory
import hidc.seorin.hidcserver.service.WorksCategoryService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "WorksCategory", description = "작품 카테고리 관리 API")
@RestController
@RequestMapping("/categories")
class WorksCategoryController(
    private val worksCategoryService: WorksCategoryService
) {
    @Operation(summary = "모든 카테고리 조회", description = "등록된 모든 카테고리 목록을 조회합니다.")
    @GetMapping
    fun findAll(): List<WorksCategory> {
        return worksCategoryService.findAll()
    }

    @Operation(summary = "카테고리 상세 조회", description = "특정 ID의 카테고리 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "카테고리 ID", required = true)
        @PathVariable id: Int
    ): ResponseEntity<WorksCategory> {
        val category = worksCategoryService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(category)
    }

    @Operation(summary = "카테고리 생성", description = "새로운 카테고리를 생성합니다.")
    @PostMapping
    fun create(@RequestBody request: CreateWorksCategoryRequest): ResponseEntity<WorksCategory> {
        val category = worksCategoryService.create(request)
        return ResponseEntity.ok(category)
    }

    @Operation(summary = "카테고리 수정", description = "카테고리 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "카테고리 ID", required = true)
        @PathVariable id: Int,
        @RequestBody request: UpdateWorksCategoryRequest
    ): ResponseEntity<WorksCategory> {
        val category = worksCategoryService.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(category)
    }

    @Operation(summary = "카테고리 삭제", description = "카테고리를 삭제합니다.")
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "카테고리 ID", required = true)
        @PathVariable id: Int
    ): ResponseEntity<Void> {
        return if (worksCategoryService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

