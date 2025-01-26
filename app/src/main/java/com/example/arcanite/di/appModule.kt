package com.example.arcanite.di

import com.example.arcanite.ui.viewModels.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        UsersViewModel(
            searchUsersUseCase = get()
        )
    }
}