package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.Works
import org.springframework.data.jpa.repository.JpaRepository

interface WorksRepository : JpaRepository<Works, Long>

