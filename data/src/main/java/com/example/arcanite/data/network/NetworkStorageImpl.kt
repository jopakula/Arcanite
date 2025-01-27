package com.example.arcanite.data.network

import com.example.arcanite.data.NetworkStorage
import com.example.arcanite.data.network.models.file.GitHubFile
import com.example.arcanite.data.network.models.repository.GitHubRepositoriesResponse
import com.example.arcanite.data.network.models.user.GitHubUsersResponse

class NetworkStorageImpl(
    private val gitHubApi: GitHubApi
) : NetworkStorage {
    override suspend fun searchUsers(query: String): GitHubUsersResponse {
        return gitHubApi.searchUsers(query = query)
    }

    override suspend fun searchRepositories(query: String): GitHubRepositoriesResponse {
        return gitHubApi.searchRepositories(query = query)
    }

    override suspend fun getRepositoriesContents(owner: String, repo: String, path: String): List<GitHubFile> {
        return gitHubApi.getRepositoriesContents(owner = owner, repo = repo, path = path)
    }
}