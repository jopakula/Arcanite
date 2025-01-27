package com.example.arcanite.data.mappers

import com.example.arcanite.data.network.models.user.GitHubUser
import com.example.arcanite.data.network.models.user.GitHubUsersResponse
import com.example.arcanite.domain.models.user.User
import com.example.arcanite.domain.models.user.UsersResponse

class UserMapper {
    companion object{
        fun mapUsersResponseDataToDomain(response: GitHubUsersResponse): UsersResponse {
            return UsersResponse(
                users = response.items?.map { mapUserDataToDomain(it) } ?: emptyList()
            )
        }

        private fun mapUserDataToDomain(user: GitHubUser): User {
            return User(
                login = user.login,
                avatarUrl = user.avatarUrl,
                htmlUrl = user.htmlUrl,
            )
        }
    }
}