package dev.vrba.homecooking.server.model.domain

data class MealPostData(
    val title: String,
    val description: String? = null,
    val imageUrl: String,
    val ingredientsList: String? = null,
    val recipe: String? = null,
    val rating: Int? = null,
    val priceCzkPerPortion: Int? = null,
    val kcalPerPortion: Int? = null,
    val preparationTimeMins: Int? = null
)