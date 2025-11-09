package hidc.seorin.hidcserver.dto

data class CreateDesignersRequest(
    val name: String?,
    val enName: String?,
    val imageUrl: String?,
    val email: String?,
    val profileUrl: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?,
    val behanceUrl: String?,
    val worksId: Long?
)

