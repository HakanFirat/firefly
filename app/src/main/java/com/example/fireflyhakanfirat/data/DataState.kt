package com.example.fireflyhakanfirat.data

/**
 * DataState of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<DataResource<T>>` to pass back the latest data to the UI with its fetch dataState.
 */
enum class DataState {
    SUCCESS,
    ERROR,
    LOADING
}