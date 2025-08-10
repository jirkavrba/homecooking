package dev.vrba.homecooking.server.repository

import dev.vrba.homecooking.server.model.MealPost
import dev.vrba.homecooking.server.model.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MealPostRepository : ListCrudRepository<MealPost, Int> {

    fun findAllByUser(user: AggregateReference<User, Int>): List<MealPost>

    fun findAllByUserIn(users: Set<AggregateReference<User, Int>>, page: Pageable): List<MealPost>

}