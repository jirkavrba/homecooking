package dev.vrba.homecooking.server.rest.response.dto

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.model.domain.MealPostWithAuthor

data class MealPostWithAuthorDto(
    @JsonProperty("post")
    val post: MealPostDto,
    @JsonProperty("author")
    val author: PostAuthorDto
)

fun MealPostWithAuthor.toDto() =
    MealPostWithAuthorDto(
        post = post.toDto(),
        author = author.toDto()
    )