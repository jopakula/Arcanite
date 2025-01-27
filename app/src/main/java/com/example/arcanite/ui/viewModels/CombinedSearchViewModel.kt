package com.example.arcanite.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.domain.useCases.SearchRepositoriesUseCase
import com.example.arcanite.domain.useCases.SearchUsersUseCase
import com.example.arcanite.ui.screens.SearchResultItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CombinedSearchViewModel(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    private val combinedStateMutable = MutableStateFlow<RequestState<List<SearchResultItem>>>(RequestState.Idle)
    private val combinedState: StateFlow<RequestState<List<SearchResultItem>>> = combinedStateMutable

    fun search(query: String) {
        if (query.isBlank()) {
            combinedStateMutable.value = RequestState.Empty
            return
        }

        combinedStateMutable.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val usersDeferred = searchUsersUseCase.execute(query).users ?: emptyList()
                val reposDeferred = searchRepositoriesUseCase.execute(query).items ?: emptyList()

                val users = usersDeferred.map { SearchResultItem.UserItem(it) }
                val repos = reposDeferred.map { SearchResultItem.RepoItem(it) }

                val combinedList = (users + repos).sortedBy {
                    when (it) {
                        is SearchResultItem.UserItem -> it.user.login?.lowercase()
                        is SearchResultItem.RepoItem -> it.repo.name?.lowercase()
                    }
                }

                if (combinedList.isEmpty()) {
                    combinedStateMutable.value = RequestState.Empty
                } else {
                    combinedStateMutable.value = RequestState.Success(combinedList)
                }
            } catch (e: Exception) {
                combinedStateMutable.value = RequestState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun combinedStateFlow(): StateFlow<RequestState<List<SearchResultItem>>> = combinedState
}
