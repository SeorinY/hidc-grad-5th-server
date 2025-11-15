package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.domain.ProfessorDomain
import hidc.seorin.hidcserver.dto.CreateProfessorRequest
import hidc.seorin.hidcserver.dto.UpdateProfessorRequest
import hidc.seorin.hidcserver.service.ProfessorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Professor", description = "교수 관리 API")
@RestController
@RequestMapping("/professors")
class ProfessorController(
    private val professorService: ProfessorService
) {
    @Operation(summary = "모든 교수 조회", description = "등록된 모든 교수 목록을 조회합니다.")
    @GetMapping
    fun findAll(): List<ProfessorDomain> {
        return professorService.findAll()
    }

    @Operation(summary = "교수 상세 조회", description = "특정 ID의 교수 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "교수 ID", required = true)
        @PathVariable id: Int
    ): ResponseEntity<ProfessorDomain> {
        val professor = professorService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(professor)
    }

    @Operation(summary = "교수 생성", description = "새로운 교수를 생성합니다.")
    @PostMapping
    fun create(@RequestBody request: CreateProfessorRequest): ResponseEntity<ProfessorDomain> {
        val professor = professorService.create(request)
        return ResponseEntity.ok(professor)
    }

    @Operation(summary = "교수 수정", description = "교수 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "교수 ID", required = true)
        @PathVariable id: Int,
        @RequestBody request: UpdateProfessorRequest
    ): ResponseEntity<ProfessorDomain> {
        val professor = professorService.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(professor)
    }

    @Operation(summary = "교수 삭제", description = "교수를 삭제합니다.")
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "교수 ID", required = true)
        @PathVariable id: Int
    ): ResponseEntity<Void> {
        return if (professorService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

