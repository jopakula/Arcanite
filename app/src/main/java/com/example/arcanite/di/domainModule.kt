package com.example.arcanite.di

import com.example.arcanite.domain.useCases.GetRepositoriesContentsUseCase
import com.example.arcanite.domain.useCases.SearchRepositoriesUseCase
import com.example.arcanite.domain.useCases.SearchUsersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<SearchUsersUseCase> {
        SearchUsersUseCase(repository = get())
    }
    factory<SearchRepositoriesUseCase> {
        SearchRepositoriesUseCase(repository = get())
    }
    factory<GetRepositoriesContentsUseCase> {
        GetRepositoriesContentsUseCase(repository = get())
    }

}
