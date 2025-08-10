package dev.vrba.homecooking.server.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "homecooking.integration.image-kit")
data class ImageKitConfigurationProperties(
    val urlEndpoint: String,
    val privateKey: String,
    val publicKey: String,
)