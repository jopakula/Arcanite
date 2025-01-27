package com.example.arcanite.data.mappers

import com.example.arcanite.data.network.models.repository.GitHubRepositoriesResponse
import com.example.arcanite.data.network.models.repository.GitHubRepository
import com.example.arcanite.data.network.models.user.GitHubUser
import com.example.arcanite.domain.models.repository.RepositoriesResponse
import com.example.arcanite.domain.models.repository.Repository
import com.example.arcanite.domain.models.user.User

class RepositoryMapper {
    companion object{
        fun mapRepositoryResponseDataToDomain(response: GitHubRepositoriesResponse): RepositoriesResponse {
            return RepositoriesResponse(
                items = response.items?.map { mapRepositoryDataToDomain(it) } ?: emptyList()
            )
        }

        private fun mapRepositoryDataToDomain(repository: GitHubRepository): Repository {
            return Repository(
                name = repository.name,
                description = repository.description,
                htmlUrl = repository.htmlUrl,
                owner = repository.owner?.let { mapOwnerDataToDomain(it) } ?: User(login = "", avatarUrl = "", htmlUrl = ""),
                stars = repository.stars,
                watchers = repository.watchers,
                forks = repository.forks,
                createdAt = repository.createdAt,
                updatedAt = repository.updatedAt
            )
        }

        private fun mapOwnerDataToDomain(user: GitHubUser): User{
            return User(
                login = user.login,
                avatarUrl = user.avatarUrl,
                htmlUrl = user.htmlUrl
            )
        }
    }
}