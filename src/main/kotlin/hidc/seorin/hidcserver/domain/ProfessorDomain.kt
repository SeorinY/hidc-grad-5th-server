package hidc.seorin.hidcserver.domain

import hidc.seorin.hidcserver.entity.Professor

data class ProfessorDomain(
    val id: Int?,
    val name: String?,
    val className: String?,
    var works: List<WorksDomain>? = null
) {
    companion object {
        fun from(entity: Professor): ProfessorDomain {
            return ProfessorDomain(
                id = entity.id,
                name = entity.name,
                className = entity.className
            )
        }
    }
}

