package hidc.seorin.hidcserver.dto

data class UpdateWorksFileRequest(
    val fileUrl: String,
    val seq: Int?,
    val worksId: Long
)

