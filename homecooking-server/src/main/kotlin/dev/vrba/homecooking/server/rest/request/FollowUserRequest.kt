package dev.vrba.homecooking.server.rest.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class FollowUserRequest(
    @NotBlank
    @JsonProperty("follow_code")
    val code: String
)