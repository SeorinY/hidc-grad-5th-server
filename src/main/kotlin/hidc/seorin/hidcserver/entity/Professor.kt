package hidc.seorin.hidcserver.entity

import jakarta.persistence.*

@Entity
@Table(name = "professor")
data class Professor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(length = 100)
    val name: String? = null,

    @Column(name = "class_name", length = 100)
    val className: String? = null,

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    val works: List<Works> = emptyList()
)

