package dev.vrba.homecooking.server.model

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("followed_users")
data class FollowedUser(
    @Id
    @Column("id")
    val id: Int,
    @Column("followed_user_id")
    val followedUser: AggregateReference<User, Int>
)