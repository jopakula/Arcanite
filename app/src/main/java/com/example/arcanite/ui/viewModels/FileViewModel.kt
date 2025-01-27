package com.example.arcanite.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcanite.data.network.RequestState
import com.example.arcanite.domain.models.file.File
import com.example.arcanite.domain.useCases.GetRepositoriesContentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FileViewModel(private val getRepositoriesContentsUseCase: GetRepositoriesContentsUseCase) : ViewModel() {

    private val _filesState = MutableStateFlow<RequestState<List<File>>>(RequestState.Idle)
    val filesState: StateFlow<RequestState<List<File>>> = _filesState

    fun loadFiles(owner: String, repo: String, path: String) {
        _filesState.value = RequestState.Loading

        viewModelScope.launch {
            try {
                val files = getRepositoriesContentsUseCase.execute(owner, repo, path)
                if (files.isEmpty()) {
                    _filesState.value = RequestState.Empty
                } else {
                    _filesState.value = RequestState.Success(files)
                }
            } catch (e: Exception) {
                _filesState.value = RequestState.Error("Ошибка загрузки файлов")
            }
        }
    }
}
