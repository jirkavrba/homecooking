package dev.vrba.homecooking.server.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @Column("id")
    val id: Int,
    @Column("username")
    val username: String,
    @Column("avatar_url")
    val avatar: String,
    @Column("discord_id")
    val discordId: String,
    @Column("token")
    val token: String? = null
)
