package com.example.arcanite.domain.useCases

import com.example.arcanite.domain.Repository
import com.example.arcanite.domain.models.UsersResponse

class SearchUsersUseCase(private val repository: Repository) {
    suspend fun execute(query: String): UsersResponse{
        return repository.searchUsers(query = query)
    }
}