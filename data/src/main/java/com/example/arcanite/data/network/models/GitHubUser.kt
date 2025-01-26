package com.example.arcanite.data.network.models

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("html_url") val htmlUrl: String?
)