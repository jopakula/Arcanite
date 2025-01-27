package com.example.arcanite.data.network.models.repository

import com.example.arcanite.data.network.models.user.GitHubUser
import com.google.gson.annotations.SerializedName

data class GitHubRepository(
    val name: String?,
    val description: String?,
    val htmlUrl: String?,
    val owner: GitHubUser?,
    @SerializedName("stargazers_count") val stars: Int?,
    @SerializedName("watchers_count") val watchers: Int?,
    @SerializedName("forks_count") val forks: Int?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

