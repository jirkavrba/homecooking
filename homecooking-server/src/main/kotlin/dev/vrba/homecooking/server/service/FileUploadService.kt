package dev.vrba.homecooking.server.service

import dev.vrba.homecooking.server.exception.UnsupportedFileException
import dev.vrba.homecooking.server.model.UploadedFile
import dev.vrba.homecooking.server.model.User
import dev.vrba.homecooking.server.repository.UploadedFileRepository
import io.imagekit.sdk.ImageKit
import io.imagekit.sdk.models.FileCreateRequest
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class FileUploadService(
    private val imageKit: ImageKit,
    private val repository: UploadedFileRepository
) {
    // 25 MB
    private val maxFileSize = 25 * 1024 * 1024;

    private val supportedMediaTypes = setOf(
        MediaType.IMAGE_PNG_VALUE,
        MediaType.IMAGE_JPEG_VALUE,
    )

    private val supportedMediaExtensions = setOf(
        "png",
        "jpg",
        "jpeg",
    )

    fun uploadFile(user: User, file: MultipartFile): UploadedFile {
        if (file.size >= maxFileSize) {
            throw UnsupportedFileException()
        }

        if (file.contentType !in supportedMediaTypes) {
            throw UnsupportedFileException()
        }

        val extension = file.originalFilename
            ?.split(".")
            ?.lastOrNull()
            ?: throw UnsupportedFileException()

        if (extension !in supportedMediaExtensions) {
            throw UnsupportedFileException()
        }

        val name = "${UUID.randomUUID()}.${extension}"
        val request = FileCreateRequest(file.bytes, name)
        val response = imageKit.upload(request)
        val file = UploadedFile(
            id = 0,
            fileUrl = response.url,
            user = AggregateReference.to(user.id)
        )

        return repository.save(file)
    }
}