package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.rest.request.LoginRequest
import dev.vrba.homecooking.server.rest.response.LoginResponse
import dev.vrba.homecooking.server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val userService: UserService
) {

    @PostMapping("/login/magic-link")
    fun loginWithMagicLink(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        val user = userService.loginWithMagicLink(request.magicLinkToken)

        return user
            ?.let { ResponseEntity.ok(LoginResponse(it.token.orEmpty())) }
            ?: ResponseEntity.notFound().build()
    }
}