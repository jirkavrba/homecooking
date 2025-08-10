package dev.vrba.homecooking.server.configuration

import io.imagekit.sdk.ImageKit
import io.imagekit.sdk.config.Configuration as ImageKitConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ImageKitConfigurationProperties::class)
class ImageKitConfiguration {

    @Bean
    fun imageKitSdk(properties: ImageKitConfigurationProperties): ImageKit {
        return ImageKit.getInstance().apply {
            config = ImageKitConfiguration(
                properties.publicKey,
                properties.privateKey,
                properties.urlEndpoint
            )
        }
    }
}