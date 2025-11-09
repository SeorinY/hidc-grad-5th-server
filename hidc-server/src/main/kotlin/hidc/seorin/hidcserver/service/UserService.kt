package hidc.seorin.hidcserver.service

import hidc.seorin.hidcserver.entity.User
import hidc.seorin.hidcserver.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}

