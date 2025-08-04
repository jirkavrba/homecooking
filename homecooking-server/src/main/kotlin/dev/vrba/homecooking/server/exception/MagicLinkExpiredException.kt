package dev.vrba.homecooking.server.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class MagicLinkExpiredException : RuntimeException("The provided magic link is expired.")