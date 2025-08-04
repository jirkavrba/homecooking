package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginResponse(
    @JsonProperty("auth_token")
    val token: String
)