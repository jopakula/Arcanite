package com.example.arcanite.domain.useCases

import com.example.arcanite.domain.Repository
import com.example.arcanite.domain.models.file.File

class GetRepositoriesContentsUseCase(private val repository: Repository) {
    suspend fun execute(owner: String, repo: String, path: String): List<File> {
        return repository.getRepositoriesContents(owner, repo, path)
    }
}
