package com.example.arcanite.domain

import com.example.arcanite.domain.models.repository.RepositoriesResponse
import com.example.arcanite.domain.models.user.UsersResponse

interface Repository {

    suspend fun searchUsers(query: String): UsersResponse

    suspend fun searchRepositories(query: String): RepositoriesResponse
}