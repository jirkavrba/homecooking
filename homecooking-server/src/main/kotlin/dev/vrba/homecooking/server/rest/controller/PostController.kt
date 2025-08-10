package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.rest.response.PostsFeedResponse
import dev.vrba.homecooking.server.rest.response.dto.toDto
import dev.vrba.homecooking.server.service.MealPostService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user/post")
class PostController(
    private val service: MealPostService
) {

    @GetMapping("/feed")
    fun feed(): ResponseEntity<PostsFeedResponse> {
        return TODO("Feed requires following to be implemented among users.")
    }

    @GetMapping("/my-posts")

    fun list(@AuthenticationPrincipal user: User): ResponseEntity<PostsFeedResponse> {
        val posts = service.getAllPostsByUser(user)
        val response = PostsFeedResponse(posts = posts.map { it.toDto() })

        return ResponseEntity.ok(response)
    }
}