package dev.vrba.homecooking.server.rest.controller

import dev.vrba.homecooking.server.rest.request.MagicLinkRequest
import dev.vrba.homecooking.server.rest.response.MagicLinkResponse
import dev.vrba.homecooking.server.service.MagicLinkService
import dev.vrba.homecooking.server.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/api/v1/bot")
class BotController(
    private val userService: UserService,
    private val magicLinkService: MagicLinkService
) {

    @PostMapping("/user/magic-link")
    fun generateMagicLink(@RequestBody request: MagicLinkRequest): ResponseEntity<MagicLinkResponse> {
        val user = userService.upsertUser(request.discordId, request.username, request.avatar)
        val link = magicLinkService.generateMagicLinkFor(user)
        val response = MagicLinkResponse(link)

        return ResponseEntity.ok(response)
    }
}