package hidc.seorin.hidcserver.controller

import hidc.seorin.hidcserver.entity.User
import hidc.seorin.hidcserver.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("/hello")
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }
}

