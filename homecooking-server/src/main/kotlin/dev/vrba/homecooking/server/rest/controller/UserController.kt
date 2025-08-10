package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.rest.request.FollowUserRequest
import dev.vrba.homecooking.server.rest.response.UserInfoResponse
import dev.vrba.homecooking.server.rest.response.toResponse
import dev.vrba.homecooking.server.service.UserService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SecurityRequirement(name = "user")
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun getUserInfo(@AuthenticationPrincipal user: User): ResponseEntity<UserInfoResponse> {
        val response = user.toResponse()

        return ResponseEntity.ok(response)
    }

    @PostMapping("/follow")
    fun followUser(@AuthenticationPrincipal user: User, @RequestBody request: FollowUserRequest): ResponseEntity<UserInfoResponse> {
        val followedUser = userService.follow(user, request.code)
        val response = followedUser.toResponse()

        return ResponseEntity.ok(response)
    }


    @PostMapping("/regenerate-follow-code")
    fun regenerateFollowCode(@AuthenticationPrincipal user: User): ResponseEntity<UserInfoResponse> {
        val updatedUser = userService.regenerateFollowCode(user)
        val response = updatedUser.toResponse()

        return ResponseEntity.ok(response)
    }
}
