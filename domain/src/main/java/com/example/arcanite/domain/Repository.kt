package com.example.arcanite.domain

import com.example.arcanite.domain.models.UsersResponse

interface Repository {

    suspend fun searchUsers(query: String): UsersResponse
}