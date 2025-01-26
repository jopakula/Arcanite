package com.example.arcanite.di

import com.example.arcanite.data.NetworkStorage
import com.example.arcanite.data.RepositoryImpl
import com.example.arcanite.data.network.ApiClient
import com.example.arcanite.data.network.GitHubApi
import com.example.arcanite.data.network.NetworkStorageImpl
import com.example.arcanite.domain.Repository
import org.koin.dsl.module


val dataModule = module {

    single<GitHubApi> { ApiClient.api }
    single<NetworkStorage> { NetworkStorageImpl(gitHubApi = get()) }
    single<Repository> { RepositoryImpl(networkStorage = get())  }

}