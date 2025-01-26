package com.example.arcanite.data

import com.example.arcanite.data.network.models.GitHubUsersResponse

interface NetworkStorage {

    suspend fun searchUsers(query: String): GitHubUsersResponse
}