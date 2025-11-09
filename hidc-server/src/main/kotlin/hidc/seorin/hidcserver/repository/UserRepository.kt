package hidc.seorin.hidcserver.repository

import hidc.seorin.hidcserver.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>

