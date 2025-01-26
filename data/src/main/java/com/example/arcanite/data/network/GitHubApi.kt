package com.example.arcanite.data.network

import com.example.arcanite.data.network.models.GitHubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): GitHubUsersResponse
}
