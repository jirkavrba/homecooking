package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repository: UserRepository,
    private val magicLinkService: MagicLinkService,
) {
    fun findByToken(token: String): User? {
        return repository.findByToken(token)
    }

    fun upsertUser(discordId: String, username: String, avatar: String): User {
        return repository.findByDiscordId(discordId)
            ?.let { updateUser(it, username, avatar) }
            ?: createUser(discordId, username, avatar)
    }

    fun loginWithMagicLink(magicLinkToken: String): User? {
        val link = magicLinkService.useMagicLink(magicLinkToken) ?: return null
        val user = repository.findByIdOrNull(link.user.id ?: return null)

        return user?.let {
            val token = UUID.randomUUID().toString()
            val updated = it.copy(token = token)

            repository.save(updated)
        }
    }

    private fun updateUser(user: User, username: String, avatar: String): User {
        val updated = user.copy(
            username = username,
            avatar = avatar
        )

        return repository.save(updated)
    }

    private fun createUser(discordId: String, username: String, avatar: String): User {
        val user = User(
            id = 0,
            username = username,
            avatar = avatar,
            discordId = discordId
        )

        return repository.save(user)
    }
}