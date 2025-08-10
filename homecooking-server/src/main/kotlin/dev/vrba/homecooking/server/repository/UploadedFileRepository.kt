package dev.vrba.homecooking.server.repository

import dev.vrba.homecooking.server.model.UploadedFile
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UploadedFileRepository : ListCrudRepository<UploadedFile, Int> {
}