package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.configuration.MagicLinkConfiguration
import dev.vrba.homecooking.server.exception.ExpiredLoginLinkException
import dev.vrba.homecooking.server.model.MagicLink
import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.MagicLinkRepository
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.OffsetDateTime
import java.util.UUID

@Service
@EnableConfigurationProperties(MagicLinkConfiguration::class)
class MagicLinkService(
    private val clock: Clock,
    private val repository: MagicLinkRepository,
    private val configuration: MagicLinkConfiguration
) {

    fun generateMagicLinkFor(user: User): String {
        val token = UUID.randomUUID().toString()
        val userReference = AggregateReference.to<User, Int>(user.id)
        val expiration = OffsetDateTime.now(clock).plusHours(configuration.expirationHours)
        val magicLink = MagicLink(
            id = 0,
            token = token,
            user = userReference,
            expiration = expiration,
        )

        repository.save(magicLink)

        return configuration.baseUrl + token;
    }

    fun useMagicLink(token: String): MagicLink? {
        val link = repository.findByToken(token) ?: return null
        val expired = link.expiration.isBefore(OffsetDateTime.now(clock))

        if (expired) {
            throw ExpiredLoginLinkException()
        }

        repository.delete(link)

        return link
    }
}