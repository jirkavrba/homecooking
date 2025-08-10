package dev.vrba.homecooking.server.rest.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import org.springframework.data.relational.core.mapping.Column


data class CreatePostRequest(
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
    @Min(1)
    @Max(5)
    @JsonProperty("rating")
    val rating: Int? = null,
    @Min(0)
    @Column("price_czk_per_portion")
    val priceCzkPerPortion: Int? = null,
    @Min(0)
    @Column("kcal_per_portion")
    val kcalPerPortion: Int? = null,
    @Min(0)
    @Column("preparation_time_mins")
    val preparationTimeMins: Int? = null,
)