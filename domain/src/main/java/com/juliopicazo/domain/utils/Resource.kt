package com.juliopicazo.domain.utils

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> loading(data: T? = null): Resource<T> = Resource(Status.LOADING, data, null)
    }
}
