package hidc.seorin.hidcserver.domain

import hidc.seorin.hidcserver.entity.WorksFile

data class WorksFileDomain(
    val id: Long?,
    val fileUrl: String?,
    val fileType: WorksFile.FileType?,
    val seq: Int?,
    var works: WorksDomain? = null
) {
    companion object {
        fun from(entity: WorksFile): WorksFileDomain {
            return WorksFileDomain(
                id = entity.id,
                fileUrl = entity.fileUrl,
                fileType = entity.fileType,
                seq = entity.seq
            )
        }
    }
}

