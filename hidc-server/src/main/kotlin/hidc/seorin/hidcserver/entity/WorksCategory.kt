package hidc.seorin.hidcserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "works_category")
data class WorksCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(length = 256)
    val name: String? = null,

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    val works: List<Works> = emptyList()
)

