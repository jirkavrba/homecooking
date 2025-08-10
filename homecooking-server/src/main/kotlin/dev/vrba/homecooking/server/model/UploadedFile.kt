package dev.vrba.homecooking.server.model

import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.core.mapping.AggregateReference
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("uploaded_files")
data class UploadedFile(
    @Id
    @Column("id")
    val id: Int,

    @Column("file_url")
    val fileUrl: String,

    @Column("user_id")
    val user: AggregateReference<User, Int>,
)