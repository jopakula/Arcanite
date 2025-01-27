package com.example.arcanite.data.network.models.file

import com.google.gson.annotations.SerializedName

data class GitHubFile(
    val name: String?,
    val path: String?,
    val type: String?,
    val size: Int?,
    val url: String?,
    @SerializedName("download_url") val downloadUrl: String?
)