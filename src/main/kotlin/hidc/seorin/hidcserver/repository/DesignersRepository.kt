package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.Designers
import org.springframework.data.jpa.repository.JpaRepository

interface DesignersRepository : JpaRepository<Designers, Long> {
    fun findByNameContaining(name: String): List<Designers>
}

