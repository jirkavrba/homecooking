package dev.vrba.homecooking.server.rest.request

import com.fasterxml.jackson.annotation.JsonProperty

data class MagicLinkRequest(
    @JsonProperty("discord_id")
    val discordId: String,
    @JsonProperty("username")
    val username: String,
    @JsonProperty("avatar")
    val avatar: String,
)