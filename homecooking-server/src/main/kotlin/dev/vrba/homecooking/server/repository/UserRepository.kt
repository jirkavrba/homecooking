package dev.vrba.homecooking.server.repository

import dev.vrba.homecooking.server.model.User
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ListCrudRepository<User, Int> {

    fun findByToken(token: String): User?

    fun findByDiscordId(discordId: String): User?

}