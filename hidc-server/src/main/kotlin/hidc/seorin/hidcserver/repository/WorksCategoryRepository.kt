package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.WorksCategory
import org.springframework.data.jpa.repository.JpaRepository

interface WorksCategoryRepository : JpaRepository<WorksCategory, Int>

