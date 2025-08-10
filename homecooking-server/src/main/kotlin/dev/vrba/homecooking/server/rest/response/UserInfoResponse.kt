package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.model.User


data class UserInfoResponse(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("avatar_url")
    val avatar: String,
    @JsonProperty("follow_code")
    val followCode: String,
)

fun User.toResponse() =
    UserInfoResponse(
        username = username,
        avatar = avatar,
        followCode = followCode,
    )