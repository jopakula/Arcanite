package com.example.arcanite.data.network.models

import com.google.gson.annotations.SerializedName

data class GitHubUsersResponse(
    @SerializedName("items") val users: List<GitHubUser>?
)
