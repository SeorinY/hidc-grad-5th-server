package hidc.seorin.hidcserver.domain

import hidc.seorin.hidcserver.entity.Works

data class WorksDomain(
    val id: Long?,
    val thumbnailImageUrl: String?,
    val imageUrl: String?,
    val name: String?,
    val description: String?,
    val enDescription: String?,
    var professor: ProfessorDomain? = null,
    var leadDesigner: DesignersDomain? = null,
    var categories: List<WorksCategoryDomain>? = null,
    var designers: List<DesignersDomain>? = null,
    var worksFiles: List<WorksFileDomain>? = null
) {
    companion object {
        fun from(entity: Works): WorksDomain {
            return WorksDomain(
                id = entity.id,
                thumbnailImageUrl = entity.thumbnailImageUrl,
                imageUrl = entity.imageUrl,
                name = entity.name,
                description = entity.description,
                enDescription = entity.enDescription
            )
        }
    }
}

