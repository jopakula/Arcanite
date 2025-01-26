package com.example.arcanite.data.network

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data object Empty : RequestState<Nothing>()
    data class Success<out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()

    fun getSuccessDataOrNull(): T? = (this as? Success<T>)?.data
    fun getErrorMessageOrNull(): String? = (this as? Error)?.message
}