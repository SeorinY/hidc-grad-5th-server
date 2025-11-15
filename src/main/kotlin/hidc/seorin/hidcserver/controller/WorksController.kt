package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.domain.WorksDomain
import hidc.seorin.hidcserver.dto.CreateWorksRequest
import hidc.seorin.hidcserver.dto.UpdateWorksRequest
import hidc.seorin.hidcserver.service.WorksService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Works", description = "작품 관리 API")
@RestController
@RequestMapping("/works")
class WorksController(
    private val worksService: WorksService
) {
    @Operation(summary = "모든 작품 조회", description = "등록된 모든 작품 목록을 조회합니다.")
    @GetMapping
    fun findAll(): List<WorksDomain> {
        return worksService.findAll()
    }

    @Operation(summary = "작품 상세 조회", description = "특정 ID의 작품 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "작품 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<WorksDomain> {
        return worksService.findById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "카테고리별 작품 조회", description = "특정 카테고리에 속한 작품 목록을 조회합니다.")
    @GetMapping("/categories/{categoryId}")
    fun findByCategoryId(
        @Parameter(description = "카테고리 ID", required = true)
        @PathVariable categoryId: Int?
    ): ResponseEntity<List<WorksDomain>> {
        return ResponseEntity.ok(
            worksService.findByCategoryId(categoryId)
        )
    }

    @Operation(summary = "작품 생성", description = "새로운 작품을 생성합니다.")
    @PostMapping
    fun create(@RequestBody request: CreateWorksRequest): ResponseEntity<WorksDomain> {
        val works = worksService.create(request)
        return ResponseEntity.ok(works)
    }

    @Operation(summary = "작품 수정", description = "작품 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "작품 ID", required = true)
        @PathVariable id: Long,
        @RequestBody request: UpdateWorksRequest
    ): ResponseEntity<WorksDomain> {
        val works = worksService.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(works)
    }

    @Operation(summary = "작품 삭제", description = "작품을 삭제합니다.")
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "작품 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        return if (worksService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
