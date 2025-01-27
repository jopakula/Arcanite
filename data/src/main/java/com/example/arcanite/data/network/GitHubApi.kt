package com.example.arcanite.data.network

import com.example.arcanite.data.network.models.repository.GitHubRepositoriesResponse
import com.example.arcanite.data.network.models.user.GitHubUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): GitHubUsersResponse

    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): GitHubRepositoriesResponse
}
