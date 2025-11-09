package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.WorksFile
import org.springframework.data.jpa.repository.JpaRepository

interface WorksFileRepository : JpaRepository<WorksFile, Long>

