package com.example.arcanite.domain.models.file

data class File(
    val name: String?,
    val path: String?,
    val type: String?,
    val size: Int?,
    val url: String?,
    val downloadUrl: String?
)