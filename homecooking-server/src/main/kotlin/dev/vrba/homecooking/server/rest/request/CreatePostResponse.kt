package dev.vrba.homecooking.server.rest.request

import dev.vrba.homecooking.server.rest.response.dto.MealPostDto

data class CreatePostResponse(
    val post: MealPostDto
)