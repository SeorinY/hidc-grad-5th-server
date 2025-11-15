package hidc.seorin.hidcserver.dto

import hidc.seorin.hidcserver.entity.WorksFile

data class CreateWorksFileRequest(
    val fileUrl: String,
    val fileType: WorksFile.FileType?,
    val seq: Int?,
    val worksId: Long
)

