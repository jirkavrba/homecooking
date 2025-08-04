package dev.vrba.homecooking.server.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "homecooking.magic-links")
data class MagicLinkConfiguration(
    val baseUrl: String,
    val expirationHours: Long
)