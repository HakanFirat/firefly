package com.example.fireflyhakanfirat.data

import com.example.fireflyhakanfirat.api.ApiErrorResponse
import com.example.fireflyhakanfirat.api.ApiResponse
import com.example.fireflyhakanfirat.api.ApiSuccessResponse

/**
 * A generic class that holds a value with its loading dataState.
 * @param <T>
</T> */
data class Resource<out T>(val dataState: DataState, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                DataState.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                DataState.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                DataState.LOADING,
                data,
                null
            )
        }

        fun <T> parse(apiResponse: ApiResponse<T>?): Resource<T> {
            return when(apiResponse){
                is ApiSuccessResponse -> success(apiResponse.body)
                is ApiErrorResponse -> error(apiResponse.errorMessage, null)
                else -> loading(null)
            }
        }
    }
}