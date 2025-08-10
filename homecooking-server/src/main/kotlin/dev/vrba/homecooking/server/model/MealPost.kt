package dev.vrba.homecooking.server.model

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.OffsetDateTime

@Table("meal_posts")
data class MealPost(
    @Id
    @Column("id")
    val id: Int,

    @Column("user_id")
    val user: AggregateReference<User, Int>,

    @Column("title")
    val title: String,

    @Column("description")
    val description: String? = null,

    @Column("image_url")
    val imageUrl: String,

    @Column("ingredients_list")
    val ingredientsList: String? = null,

    @Column("recipe")
    val recipe: String? = null,

    @Column("rating")
    val rating: Int? = null,

    @Column("price_czk_per_portion")
    val priceCzkPerPortion: Int? = null,

    @Column("kcal_per_portion")
    val kcalPerPortion: Int? = null,

    @Column("preparation_time_mins")
    val preparationTimeMins: Int? = null,

    @Column("posted_at")
    val postedAt: OffsetDateTime = OffsetDateTime.now(),
)