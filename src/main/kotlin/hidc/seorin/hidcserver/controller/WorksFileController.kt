package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.dto.CreateWorksFileRequest
import hidc.seorin.hidcserver.dto.UpdateWorksFileRequest
import hidc.seorin.hidcserver.entity.WorksFile
import hidc.seorin.hidcserver.service.WorksFileService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "WorksFile", description = "작품 파일 관리 API")
@RestController
@RequestMapping("/works-files")
class WorksFileController(
    private val worksFileService: WorksFileService
) {
    @Operation(summary = "모든 작품 파일 조회", description = "등록된 모든 작품 파일 목록을 조회합니다.")
    @GetMapping
    fun findAll(): List<WorksFile> {
        return worksFileService.findAll()
    }

    @Operation(summary = "작품 파일 상세 조회", description = "특정 ID의 작품 파일 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "작품 파일 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<WorksFile> {
        val file = worksFileService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(file)
    }

    @Operation(summary = "작품 파일 생성", description = "새로운 작품 파일을 생성합니다.")
    @PostMapping
    fun create(@RequestBody request: CreateWorksFileRequest): ResponseEntity<WorksFile> {
        val file = worksFileService.create(request)
        return ResponseEntity.ok(file)
    }

    @Operation(summary = "작품 파일 수정", description = "작품 파일 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "작품 파일 ID", required = true)
        @PathVariable id: Long,
        @RequestBody request: UpdateWorksFileRequest
    ): ResponseEntity<WorksFile> {
        val file = worksFileService.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(file)
    }

    @Operation(summary = "작품 파일 삭제", description = "작품 파일을 삭제합니다.")
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "작품 파일 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        return if (worksFileService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

