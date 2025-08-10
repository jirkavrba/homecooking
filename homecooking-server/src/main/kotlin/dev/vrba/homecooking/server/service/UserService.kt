package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.exception.InvalidFollowRequestException
import dev.vrba.homecooking.server.model.FollowedUser
import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.UserRepository
import org.springframework.data.jdbc.core.mapping.AggregateReference
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

    fun findAllByIds(userIds: Collection<Int>): Set<User> {
        return repository.findAllByIdIn(userIds).toSet()
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

    fun follow(user: User, followCode: String): User {
        val followedUser = repository.findByFollowCode(followCode) ?: throw InvalidFollowRequestException()

        val newlyFollowedUser = FollowedUser(
            id = 0,
            followedUser = AggregateReference.to(followedUser.id)
        )

        val updatedFollowedUsers = (user.followedUsers + newlyFollowedUser).distinctBy { it.followedUser.id }.toSet()
        val updatedUser = user.copy(followedUsers = updatedFollowedUsers)

        repository.save(updatedUser)

        return followedUser
    }

    fun regenerateFollowCode(user: User): User {
        return repository.save(
            user.copy(followCode = generateFollowCode())
        )
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
            discordId = discordId,
            followCode = generateFollowCode()
        )

        return repository.save(user)
    }

    private fun generateFollowCode(): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'y')
        val consonants = ('a'..'z').toSet() - vowels
        val letters = List(5) {
            val source = if (it % 2 == 0) consonants else vowels
            source.random()
        }

        return letters.joinToString("").uppercase()
    }
}