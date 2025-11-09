package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.Professor
import org.springframework.data.jpa.repository.JpaRepository

interface ProfessorRepository : JpaRepository<Professor, Int>

