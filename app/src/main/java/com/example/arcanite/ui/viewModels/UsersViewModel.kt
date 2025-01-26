package com.example.arcanite.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.domain.models.User
import com.example.arcanite.domain.useCases.SearchUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val usersStateMutable = MutableStateFlow<RequestState<List<User>>>(RequestState.Idle)
    private val usersState: StateFlow<RequestState<List<User>>> = usersStateMutable

    fun searchUsers(query: String) {
        usersStateMutable.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val response = searchUsersUseCase.execute(query)
                val users = response.users ?: emptyList()

                if (users.isEmpty()) {
                    usersStateMutable.value = RequestState.Empty
                } else {
                    usersStateMutable.value = RequestState.Success(users)
                }
            } catch (e: Exception) {
                usersStateMutable.value = RequestState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    fun usersStateFlow(): StateFlow<RequestState<List<User>>> = usersState
}
