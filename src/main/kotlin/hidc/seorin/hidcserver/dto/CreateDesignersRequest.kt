package hidc.seorin.hidcserver.dto

import jakarta.validation.constraints.NotNull

data class CreateDesignersRequest(
    val name: String?,
    val enName: String?,
    val imageUrl: String?,
    val email: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?,
    val behanceUrl: String?,
    val studentNumber: String?,
    @field:NotNull(message = "worksId는 필수입니다")
    val worksId: Long?
)

