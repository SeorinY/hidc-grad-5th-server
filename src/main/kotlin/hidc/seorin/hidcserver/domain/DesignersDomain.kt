package hidc.seorin.hidcserver.domain

import hidc.seorin.hidcserver.entity.Designers

data class DesignersDomain(
    val id: Long?,
    val name: String?,
    val enName: String?,
    val imageUrl: String?,
    val email: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?,
    val behanceUrl: String?,
    val studentNumber: String?,
    var works: WorksDomain? = null
) {
    companion object {
        fun from(entity: Designers): DesignersDomain {
            return DesignersDomain(
                id = entity.id,
                name = entity.name,
                enName = entity.enName,
                imageUrl = entity.imageUrl,
                email = entity.email,
                linkedinUrl = entity.linkedinUrl,
                instagramUrl = entity.instagramUrl,
                behanceUrl = entity.behanceUrl,
                studentNumber = entity.studentNumber,
            )
        }
    }
}

