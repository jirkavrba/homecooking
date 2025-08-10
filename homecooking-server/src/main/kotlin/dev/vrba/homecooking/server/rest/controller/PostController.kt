package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.rest.request.CreatePostRequest
import dev.vrba.homecooking.server.rest.request.CreatePostResponse
import dev.vrba.homecooking.server.rest.response.FileUploadResponse
import dev.vrba.homecooking.server.rest.response.PostsFeedResponse
import dev.vrba.homecooking.server.rest.response.PostsListingResponse
import dev.vrba.homecooking.server.rest.response.dto.toDto
import dev.vrba.homecooking.server.security.FileUploadService
import dev.vrba.homecooking.server.service.MealPostService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
@SecurityRequirement(name = "user")
@RequestMapping("/api/v1/user/post")
class PostController(
    private val mealPostService: MealPostService,
    private val fileUploadService: FileUploadService
) {

    @GetMapping("/feed")
    fun feed(
        @AuthenticationPrincipal user: User,
        @RequestParam("page") page: Int = 1
    ): ResponseEntity<PostsFeedResponse> {
        println(page)

        val posts = mealPostService.getUserFeedPosts(user)
        val response = PostsFeedResponse(posts = posts.map { it.toDto() })

        return ResponseEntity.ok(response)
    }

    @GetMapping("/my-posts")

    fun listPosts(@AuthenticationPrincipal user: User): ResponseEntity<PostsListingResponse> {
        val posts = mealPostService.getAllPostsByUser(user)
        val response = PostsListingResponse(posts = posts.map { it.toDto() })

        return ResponseEntity.ok(response)
    }

    @PostMapping("/create")
    fun createPost(
        @AuthenticationPrincipal user: User,
        @RequestBody request: CreatePostRequest
    ): ResponseEntity<CreatePostResponse> {
        val post = mealPostService.createMealPost(user, request)
        val response = CreatePostResponse(post = post.toDto())

        return ResponseEntity.ok(response)
    }

    @PostMapping(
        "/image/upload",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun uploadImage(
        @AuthenticationPrincipal user: User,
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<FileUploadResponse> {
        val uploadedFile = fileUploadService.uploadFile(user, file)
        val response = FileUploadResponse(url = uploadedFile.fileUrl)

        return ResponseEntity.ok(response)
    }
}