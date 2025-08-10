package dev.vrba.homecooking.server.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UnsupportedFileException : RuntimeException("The uploaded file is not valid.")