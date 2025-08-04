package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.rest.response.UserInfoResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @GetMapping("/me")
    fun getUserInfo(@AuthenticationPrincipal user: User): ResponseEntity<UserInfoResponse> {
        val response = UserInfoResponse(
            username = user.username,
            avatar = user.avatar
        )

        return ResponseEntity.ok(response)
    }
}