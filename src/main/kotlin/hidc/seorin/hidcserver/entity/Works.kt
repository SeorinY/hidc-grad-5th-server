package hidc.seorin.hidcserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "works")
data class Works(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(columnDefinition = "TEXT")
    val thumbnailImageUrl: String? = null,

    @Column(columnDefinition = "TEXT")
    val imageUrl: String? = null,

    @Column(length = 256)
    val name: String? = null,

    @Column(columnDefinition = "TEXT")
    val description: String? = null,

    @Column(columnDefinition = "TEXT")
    val enDescription: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    val professor: Professor? = null,

    @OneToMany(mappedBy = "works", fetch = FetchType.LAZY)
    val designers: List<Designers> = emptyList(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_designers_id", nullable = false)
    val leadDesigner: Designers? = null,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "works_works_category",
        joinColumns = [JoinColumn(name = "works_id")],
        inverseJoinColumns = [JoinColumn(name = "works_category_id")]
    )
    val categories: List<WorksCategory> = emptyList(),

    @OneToMany(mappedBy = "works", fetch = FetchType.LAZY)
    val works: List<WorksFile> = emptyList()
)

