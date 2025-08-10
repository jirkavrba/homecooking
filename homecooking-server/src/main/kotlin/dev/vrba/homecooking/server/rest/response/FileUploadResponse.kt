package dev.vrba.homecooking.server.rest.response

import com.fasterxml.jackson.annotation.JsonProperty

data class FileUploadResponse(
    @JsonProperty("file_url")
    val url: String
)