package com.example.arcanite.domain.models.repository

import com.example.arcanite.domain.models.user.User

data class Repository(
    val name: String?,
    val description: String?,
    val htmlUrl: String?,
    val owner: User?,
    val stars: Int?,
    val watchers: Int?,
    val forks: Int?,
    val createdAt: String?,
    val updatedAt: String?,
)