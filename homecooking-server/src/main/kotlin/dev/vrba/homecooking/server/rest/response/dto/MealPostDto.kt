package dev.vrba.homecooking.server.rest.response.dto

import com.fasterxml.jackson.annotation.JsonProperty
import dev.vrba.homecooking.server.model.MealPost
import java.time.OffsetDateTime

data class MealPostDto(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("image_url")
    val imageUrl: String,
    @JsonProperty("description")
    val description: String? = null,
    @JsonProperty("ingredients_list")
    val ingredientsList: String? = null,
    @JsonProperty("recipe")
    val recipe: String? = null,
    @JsonProperty("rating")
    val rating: Int? = null,
    @JsonProperty("price_czk_per_portion")
    val priceCzkPerPortion: Int? = null,
    @JsonProperty("kcal_per_portion")
    val kcalPerPortion: Int? = null,
    @JsonProperty("preparation_time_minutes")
    val preparationTimeMins: Int? = null,
    @JsonProperty("posted_at")
    val postedAt: OffsetDateTime,
)

fun MealPost.toDto() =
    MealPostDto(
        title = title,
        imageUrl = imageUrl,
        description = description,
        ingredientsList = ingredientsList,
        recipe = recipe,
        rating = rating,
        priceCzkPerPortion = priceCzkPerPortion,
        kcalPerPortion = kcalPerPortion,
        preparationTimeMins = preparationTimeMins,
        postedAt = postedAt
    )