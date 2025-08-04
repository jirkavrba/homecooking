package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty


data class UserInfoResponse(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("avatar_url")
    val avatar: String
)