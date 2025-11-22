package hidc.seorin.hidcserver.dto

import hidc.seorin.hidcserver.entity.WorksFile
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateWorksFileRequest(
    @field:NotBlank(message = "fileUrl은 필수입니다")
    val fileUrl: String,
    @field:NotNull(message = "fileType은 필수입니다")
    val fileType: WorksFile.FileType?,
    @field:NotNull(message = "seq는 필수입니다")
    val seq: Int?,
    @field:NotNull(message = "worksId는 필수입니다")
    val worksId: Long?
)

