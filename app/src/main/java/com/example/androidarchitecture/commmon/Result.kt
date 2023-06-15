package com.example.androidarchitecture.commmon

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val exception: Exception? = null, val data: T? = null) : Result<T>
    data class Loading<T>(val data: T? = null) : Result<T>
}

fun <T> Result<T>.getOrNull(): T? {
    return when (this) {
        is Result.Success -> data
        else -> null
    }
}