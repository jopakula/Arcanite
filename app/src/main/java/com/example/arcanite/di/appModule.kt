package com.example.arcanite.di

import com.example.arcanite.ui.viewModels.CombinedSearchViewModel
import com.example.arcanite.ui.viewModels.FileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        CombinedSearchViewModel(
            searchUsersUseCase = get(),
            searchRepositoriesUseCase = get()
        )
    }

    viewModel {
        FileViewModel(
           getRepositoriesContentsUseCase = get()
        )
    }
}