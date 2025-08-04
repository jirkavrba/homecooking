package dev.vrba.homecooking.server.rest.request

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginRequest(
    @JsonProperty("token")
    val magicLinkToken: String
)