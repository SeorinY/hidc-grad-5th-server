package hidc.seorin.hidcserver.dto

data class CreateWorksFileRequest(
    val fileUrl: String,
    val seq: Int?,
    val worksId: Long
)

