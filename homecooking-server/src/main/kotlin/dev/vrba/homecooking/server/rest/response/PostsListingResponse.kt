package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.rest.response.dto.MealPostDto
import dev.vrba.homecooking.server.rest.response.dto.MealPostWithAuthorDto

data class PostsListingResponse(
    @JsonProperty("posts")
    val posts: List<MealPostDto>
)