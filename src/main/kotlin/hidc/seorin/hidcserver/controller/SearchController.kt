package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.dto.SearchResult
import hidc.seorin.hidcserver.service.SearchService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Search", description = "통합 검색 API")
@RestController
@RequestMapping("/search")
class SearchController(
    private val searchService: SearchService
) {
    @Operation(
        summary = "키워드 검색",
        description = "교수 -> 디자이너 -> 작품 순서로 name 필드를 검색합니다. resultType으로 조회된 타입을 확인할 수 있습니다."
    )
    @GetMapping
    fun search(
        @Parameter(description = "검색 키워드", required = true)
        @RequestParam keyword: String
    ): ResponseEntity<SearchResult> {
        return ResponseEntity.ok(searchService.search(keyword))
    }
}
