package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.domain.SortType
import hidc.seorin.hidcserver.entity.Designers
import hidc.seorin.hidcserver.service.DesignersService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    ): List<Designers> {
        return designersService.findAll(sortType)
    }

    @Operation(summary = "디자이너 상세 조회", description = "특정 ID의 디자이너 정보를 조회합니다.")
    @GetMapping("/{id}")
    fun findById(
        @Parameter(description = "디자이너 ID", required = true)
        @PathVariable id: Long
    ): ResponseEntity<Designers> {
        val designers = designersService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(designers)
    }
}

