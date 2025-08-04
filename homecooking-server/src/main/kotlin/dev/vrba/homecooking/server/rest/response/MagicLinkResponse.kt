package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MagicLinkResponse(
    @JsonProperty("magic_link")
    val magicLink: String,
)