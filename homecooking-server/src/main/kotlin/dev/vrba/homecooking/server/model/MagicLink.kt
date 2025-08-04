package dev.vrba.homecooking.server.model

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("magic_links")
data class MagicLink(
    @Id
    @Column("id")
    val id: Int,
    @Column("token")
    val token: String,
    @Column("user_id")
    val user: AggregateReference<User, Int>,
    @Column("expiration")
    val expiration: OffsetDateTime
)
