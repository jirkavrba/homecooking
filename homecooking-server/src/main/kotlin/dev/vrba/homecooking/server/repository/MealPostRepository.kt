package dev.vrba.homecooking.server.repository

import dev.vrba.homecooking.server.model.MealPost
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MealPostRepository : ListCrudRepository<MealPost, Int> {
}