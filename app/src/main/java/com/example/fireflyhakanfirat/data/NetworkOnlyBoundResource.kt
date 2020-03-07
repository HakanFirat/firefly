package com.example.fireflyhakanfirat.data

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.fireflyhakanfirat.api.ApiEmptyResponse
import com.example.fireflyhakanfirat.api.ApiErrorResponse
import com.example.fireflyhakanfirat.api.ApiResponse
import com.example.fireflyhakanfirat.api.ApiSuccessResponse
import com.example.fireflyhakanfirat.common.AppExecutors

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * https://github.com/android/architecture-components-samples/issues/288
 */
abstract class NetworkOnlyBoundResource<ResultType, RequestType> @MainThread
internal constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<RequestType>>()

    init {
        result.value = Resource.loading(null)

        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->

            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.diskIO().execute {
                        makeDBOperation()
                        appExecutors.mainThread().execute {
                            // reload from disk whatever we had
                            result.value = Resource.success(response.body)
                            asLiveData()
                        }
                    }
                }

                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // reload from disk whatever we had
                        result.value = Resource.success(null)
                        asLiveData()
                    }
                }

                is ApiErrorResponse -> {
                    result.value = Resource.error(response.errorMessage, null);
                    onFetchFailed()
                }

            }

        }

    }

    protected fun onFetchFailed() {}

    protected open fun makeDBOperation() {}

    fun asLiveData(): LiveData<Resource<RequestType>> {
        return result
    }

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}