package dev.vrba.homecooking.server.model.domain

import dev.vrba.homecooking.server.model.MealPost
import dev.vrba.homecooking.server.model.User

data class MealPostWithAuthor(
    val post: MealPost,
    val author: User
)