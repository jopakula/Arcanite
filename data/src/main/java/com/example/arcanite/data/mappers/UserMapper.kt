package com.example.arcanite.data.mappers

import com.example.arcanite.data.network.models.GitHubUser
import com.example.arcanite.data.network.models.GitHubUsersResponse
import com.example.arcanite.domain.models.User
import com.example.arcanite.domain.models.UsersResponse

class UserMapper {
    companion object{
        fun mapUsersResponseDataToDomain(response: GitHubUsersResponse): UsersResponse{
            return UsersResponse(
                users = response.users?.map { mapUserDataToDomain(it) } ?: emptyList()
            )
        }

        private fun mapUserDataToDomain(user: GitHubUser): User{
            return User(
                login = user.login,
                avatarUrl = user.avatarUrl,
                htmlUrl = user.htmlUrl,
            )
        }
    }
}