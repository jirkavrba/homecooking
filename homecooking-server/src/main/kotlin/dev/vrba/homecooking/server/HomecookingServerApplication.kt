package dev.vrba.homecooking.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomecookingServerApplication

fun main(args: Array<String>) {
	runApplication<HomecookingServerApplication>(*args)
}
