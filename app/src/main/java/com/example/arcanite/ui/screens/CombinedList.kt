package com.example.arcanite.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arcanite.data.network.openUrl
import com.example.arcanite.domain.models.repository.Repository
import com.example.arcanite.domain.models.user.User
import com.example.arcanite.uikit.cards.RepositoryCard
import com.example.arcanite.uikit.cards.UserCard

sealed class SearchResultItem {
    data class UserItem(val user: User) : SearchResultItem()
    data class RepoItem(val repo: Repository) : SearchResultItem()
}

@Composable
fun CombinedList(
    items: List<SearchResultItem>,
    context: Context,
    onClick: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.9F))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(items) { item ->
            when (item) {

                is SearchResultItem.UserItem -> UserCard(
                    cardText = item.user.login ?: "",
                    icon = item.user.avatarUrl ?: "",
                    onClick = {
                        onClick()
                        openUrl(context, item.user.htmlUrl ?: "")
                    }
                )

                is SearchResultItem.RepoItem -> RepositoryCard(
                    cardNameText = item.repo.name ?: "",
                    stars = item.repo.stars ?: 0,
                    watchers = item.repo.watchers ?: 0,
                    forks = item.repo.forks ?: 0,
                    createdText = item.repo.createdAt ?: "",
                    updatedText = item.repo.updatedAt ?: "",
                    descriptionText = item.repo.description ?: "",
                    onMoreTextClick = {},
                    cardText = item.repo.name ?: "",
                    icon = item.repo.owner?.avatarUrl ?: "",
                    onUserCardClick = {
                        onClick()
                        openUrl(context, item.repo.owner?.htmlUrl ?: "")
                    }
                )
            }
        }
    }
}
