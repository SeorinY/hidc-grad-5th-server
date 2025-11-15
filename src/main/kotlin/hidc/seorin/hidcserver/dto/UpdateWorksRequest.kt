package hidc.seorin.hidcserver.dto

data class UpdateWorksRequest(
    val thumbnailImageUrl: String?,
    val imageUrl: String?,
    val name: String?,
    val description: String?,
    val enDescription: String?,
    val professorId: Int?,
    val leadDesignerId: Long?,
    val categoryIds: List<Int>?
)

