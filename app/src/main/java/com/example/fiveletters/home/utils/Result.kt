package com.example.fiveletters.home.utils

class Result<DataType : Any> private constructor(
    private val value: Data<DataType>,
) {

    companion object {

        fun <T : Any> success(data: T) = Result(Success(data))

        fun <T : Any> error(
            exception: Throwable,
        ) = Result(Error<T>(exception))
    }

    val isSuccess: Boolean get() = value is Success<DataType>

    val isError: Boolean get() = value is Error<DataType>

    val getData: DataType
        get() = requireNotNull(
            when (value) {
                is Success<DataType> -> value.data
                else -> null
            }
        )

    val getException
        get() = when (value) {
            is Error<DataType> -> value.exception
            else -> null
        }

    internal sealed class Data<DATA : Any>

    internal data class Success<DATA : Any>(
        val data: DATA,
    ) : Data<DATA>()

    internal data class Error<DATA : Any>(
        val exception: Throwable,
    ) : Data<DATA>()
}