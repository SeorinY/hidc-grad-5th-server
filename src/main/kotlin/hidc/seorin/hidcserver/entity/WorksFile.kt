package hidc.seorin.hidcserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "works_file")
data class WorksFile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "file_url", columnDefinition = "TEXT")
    val fileUrl: String? = null,

    val seq: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "works_id")
    val works: Works? = null,
)

