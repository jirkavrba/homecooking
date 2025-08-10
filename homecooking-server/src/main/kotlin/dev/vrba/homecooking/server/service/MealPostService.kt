package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.model.MealPost
import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.MealPostRepository
import dev.vrba.homecooking.server.rest.request.CreatePostRequest
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.OffsetDateTime

@Service
class MealPostService(
    private val clock: Clock,
    private val repository: MealPostRepository
) {
    fun createMealPost(user: User, request: CreatePostRequest): MealPost {
        val shareToken = createShareToken()
        val userReference = AggregateReference.to<User, Int>(user.id)
        val entity = MealPost(
            id = 0,
            user = userReference,
            title = request.title,
            description = request.description,
            imageUrl = request.imageUrl,
            ingredientsList = request.ingredientsList,
            recipe = request.recipe,
            rating = request.rating,
            priceCzkPerPortion = request.priceCzkPerPortion,
            kcalPerPortion = request.kcalPerPortion,
            preparationTimeMins = request.preparationTimeMins,
            shareToken = shareToken,
            postedAt = OffsetDateTime.now(clock)
        )

        return repository.save(entity)
    }

    fun getAllPostsByUser(user: User): List<MealPost> {
        val reference = AggregateReference.to<User, Int>(user.id)
        val posts = repository.findAllByUser(reference)

        return posts
    }

    private fun createShareToken(): String =
        List(16) { ('a'..'z').random() }.joinToString("")
}