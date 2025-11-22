package hidc.seorin.hidcserver.dto

import jakarta.validation.constraints.NotNull

data class CreateWorksRequest(
    val thumbnailImageUrl: String?,
    val imageUrl: String?,
    val name: String?,
    val description: String?,
    val enDescription: String?,
    @field:NotNull(message = "professorId는 필수입니다")
    val professorId: Int?,
    val leadDesignerId: Long?,
    val categoryIds: List<Int>?
)

