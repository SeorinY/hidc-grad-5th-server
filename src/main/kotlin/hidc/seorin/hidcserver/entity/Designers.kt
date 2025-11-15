package hidc.seorin.hidcserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "designers")
data class Designers(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 256)
    val name: String? = null,

    @Column(name = "en_name", length = 256)
    val enName: String? = null,

    @Column(name = "image_url", length = 256)
    val imageUrl: String? = null,

    @Column(length = 256)
    val email: String? = null,

    @Column(name = "profile_url", length = 256)
    val profileUrl: String? = null,

    @Column(name = "linkedin_url", length = 256)
    val linkedinUrl: String? = null,

    @Column(name = "instagram_url", length = 256)
    val instagramUrl: String? = null,

    @Column(name = "behance_url", length = 256)
    val behanceUrl: String? = null,

    @Column(name = "student_number", length = 256)
    val studentNumber: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "works_id")
    val works: Works? = null
)

