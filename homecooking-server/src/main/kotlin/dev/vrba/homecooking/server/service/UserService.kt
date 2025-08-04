package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun upsertUser(discordId: String, username: String, avatar: String): User {
        return repository.findByDiscordId(discordId)
            ?.let { updateUser(it, username, avatar) }
            ?: createUser(discordId, username, avatar)
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