package dev.vrba.homecooking.server.exception

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

class ExpiredLoginLinkException : HttpStatusCodeException(HttpStatus.UNPROCESSABLE_ENTITY) {
    override val message: String = "The provided login link is expired"
}