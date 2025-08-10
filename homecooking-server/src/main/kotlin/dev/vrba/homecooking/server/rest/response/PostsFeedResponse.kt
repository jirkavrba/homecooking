package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.rest.response.dto.MealPostDto

data class PostsFeedResponse(
    @JsonProperty("posts")
    val posts: List<MealPostDto>
)