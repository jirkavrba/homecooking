package dev.vrba.homecooking.server.repository

import dev.vrba.homecooking.server.model.MagicLink
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MagicLinkRepository : CrudRepository<MagicLink, Int> {
}