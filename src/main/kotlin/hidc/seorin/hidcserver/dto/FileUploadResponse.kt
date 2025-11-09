package hidc.seorin.hidcserver.dto

data class FileUploadResponse(
    val fileUrl: String,
    val fileName: String,
    val folder: String
)
