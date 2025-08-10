package dev.vrba.homecooking.server.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration


@SecurityScheme(
    name = "bot",
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
)
@SecurityScheme(
    name = "user",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
@OpenAPIDefinition(
    info = Info(
        title = "Homecooking API",
        version = "dev",
        contact = Contact(
            name = "Jiří Vrba",
            email = "homecooking@vrba.dev",
        )
    ),
)
@Configuration
class OpenApiConfiguration
