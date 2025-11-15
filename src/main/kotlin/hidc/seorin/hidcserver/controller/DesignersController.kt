package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.domain.DesignersDomain
import hidc.seorin.hidcserver.domain.SortType
import hidc.seorin.hidcserver.dto.CreateDesignersRequest
import hidc.seorin.hidcserver.dto.UpdateDesignersRequest
import hidc.seorin.hidcserver.service.DesignersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Designers", description = "디자이너 관리 API")
@RestController
@RequestMapping("/designers")
class DesignersController(
    private val designersService: DesignersService
) {
    @Operation(
        summary = "모든 디자이너 조회",
        description = "등록된 모든 디자이너 목록을 조회합니다. sortType: RANDOM(기본, 랜덤 섞기), ASC(이름 오름차순), DESC(이름 내림차순)"
    )
    @GetMapping
    fun findAll(
        @Parameter(description = "정렬 타입 (RANDOM: 랜덤, ASC: 오름차순, DESC: 내림차순)", required = false)
        @RequestParam(required = false, defaultValue = "RANDOM") sortType: SortType
    ): List<DesignersDomain> {
        return designersService.findAll(sortType)
    }

    @Operation(summary = "디자이너 상세 조회", description = "특정 ID의 디자이너 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "디자이너 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<DesignersDomain> {
        val designers = designersService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(designers)
    }

    @Operation(summary = "디자이너 생성", description = "새로운 디자이너를 생성합니다.")
    @PostMapping
    fun create(@RequestBody request: CreateDesignersRequest): ResponseEntity<DesignersDomain> {
        val designer = designersService.create(request)
        return ResponseEntity.ok(designer)
    }

    @Operation(summary = "디자이너 수정", description = "디자이너 정보를 수정합니다.")
    @PutMapping("/{id}")
    fun update(
        @Parameter(description = "디자이너 ID", required = true)
        @PathVariable id: Long,
        @RequestBody request: UpdateDesignersRequest
    ): ResponseEntity<DesignersDomain> {
        val designer = designersService.update(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(designer)
    }

    @Operation(summary = "디자이너 삭제", description = "디자이너를 삭제합니다.")
    @DeleteMapping("/{id}")
    fun delete(
        @Parameter(description = "디자이너 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        return if (designersService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

