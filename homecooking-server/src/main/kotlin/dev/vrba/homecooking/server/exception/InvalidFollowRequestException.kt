package dev.vrba.homecooking.server.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
class InvalidFollowRequestException : RuntimeException("Invalid request for following users.")