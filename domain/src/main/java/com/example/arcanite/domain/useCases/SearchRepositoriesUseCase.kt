package com.example.arcanite.domain.useCases

import com.example.arcanite.domain.Repository
import com.example.arcanite.domain.models.repository.RepositoriesResponse


class SearchRepositoriesUseCase(private val repository: Repository) {
    suspend fun execute(query: String): RepositoriesResponse {
        return repository.searchRepositories(query = query)
    }
}