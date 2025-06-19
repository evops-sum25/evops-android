package com.example.evops.core.common

sealed class Result<T>(open val data: T?) {
    data class Success<T>(override val data: T) : Result<T>(data)
    data class Error<T>(val message: String, override val data: T? = null) : Result<T>(data)
    data class Loading<T>(override val data: T? = null) : Result<T>(data)
}