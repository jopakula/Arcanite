package com.example.arcanite.data.network

import com.example.arcanite.data.NetworkStorage
import com.example.arcanite.data.network.models.GitHubUsersResponse

class NetworkStorageImpl(
    private val gitHubApi: GitHubApi
) : NetworkStorage {
    override suspend fun searchUsers(query: String): GitHubUsersResponse {
        return gitHubApi.searchUsers(query = query)
    }
}