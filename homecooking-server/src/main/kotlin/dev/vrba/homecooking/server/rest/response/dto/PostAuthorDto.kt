package dev.vrba.homecooking.server.rest.response.dto

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.model.User

data class PostAuthorDto(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("avatar_url")
    val avatar: String
)

fun User.toDto() =
    PostAuthorDto(
        username = username,
        avatar = avatar
    )