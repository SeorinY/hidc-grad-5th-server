package hidc.seorin.hidcserver.dto

data class CreateWorksRequest(
    val name: String?,
    val description: String?,
    val enDescription: String?,
    val professorId: Int?,
    val categoryIds: List<Int>?
)

