package hidc.seorin.hidcserver.domain

import hidc.seorin.hidcserver.entity.WorksCategory

data class WorksCategoryDomain(
    val id: Int?,
    val name: String?,
    var works: List<WorksDomain>? = null
) {
    companion object {
        fun from(entity: WorksCategory): WorksCategoryDomain {
            return WorksCategoryDomain(
                id = entity.id,
                name = entity.name
            )
        }
    }
}

