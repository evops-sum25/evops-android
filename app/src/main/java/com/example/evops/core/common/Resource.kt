package com.example.evops.core.common

sealed class Resource<T>(open val data: T?) {
    data class Success<T>(override val data: T) : Resource<T>(data)

    data class Error<T>(val message: String, override val data: T? = null) : Resource<T>(data)

    data class Loading<T>(override val data: T? = null) : Resource<T>(data)
}
