package com.example.arcanite.data

import com.example.arcanite.data.mappers.UserMapper
import com.example.arcanite.domain.Repository
import com.example.arcanite.domain.models.UsersResponse

class RepositoryImpl(
    private val networkStorage: NetworkStorage
): Repository {
    override suspend fun searchUsers(query: String): UsersResponse {
        val networkData = networkStorage.searchUsers(query = query)
        return UserMapper.mapUsersResponseDataToDomain(response = networkData)
    }
}