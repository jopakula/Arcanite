package com.example.arcanite.data

import com.example.arcanite.data.network.models.file.GitHubFile
import com.example.arcanite.data.network.models.repository.GitHubRepositoriesResponse
import com.example.arcanite.data.network.models.user.GitHubUsersResponse

interface NetworkStorage {

    suspend fun searchUsers(query: String): GitHubUsersResponse

    suspend fun searchRepositories(query: String): GitHubRepositoriesResponse

    suspend fun getRepositoriesContents(owner: String, repo: String, path: String): List<GitHubFile>
}