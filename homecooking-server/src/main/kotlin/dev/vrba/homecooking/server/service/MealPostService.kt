package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.model.MealPost
import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.model.domain.MealPostData
import dev.vrba.homecooking.server.repository.MealPostRepository
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.OffsetDateTime

@Service
class MealPostService(
    private val clock: Clock,
    private val repository: MealPostRepository
) {
    fun createMealPost(user: User, data: MealPostData): MealPost {
        val userReference = AggregateReference.to<User, Int>(user.id)
        val entity = MealPost(
            id = 0,
            user = userReference,
            title = data.title,
            description = data.description,
            imageUrl = data.imageUrl,
            ingredientsList = data.ingredientsList,
            recipe = data.recipe,
            rating = data.rating,
            postedAt = OffsetDateTime.now(clock)
        )

        return repository.save(entity)
    }
}