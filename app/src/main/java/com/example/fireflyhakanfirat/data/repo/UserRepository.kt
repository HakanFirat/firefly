package com.example.fireflyhakanfirat.data.repo

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.ApiResponse
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.api.response.UpdateUserResponse
import com.example.fireflyhakanfirat.common.AppExecutors
import com.example.fireflyhakanfirat.common.NetworkUtils
import com.example.fireflyhakanfirat.data.NetworkBoundResource
import com.example.fireflyhakanfirat.data.NetworkOnlyBoundResource
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.data.source.UserDataSource
import javax.inject.Inject
import javax.inject.Named

class UserRepository
@Inject constructor(private val appExecutors: AppExecutors,
                    private val networkUtils: NetworkUtils,
                    @Named("remote") private val userRemoteDataSource: UserDataSource,
                    @Named("local") private val userLocalDataSource: UserDataSource) {

    fun register(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>> {
        return object : NetworkOnlyBoundResource<LoginResponse, LoginResponse>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<LoginResponse>> {
                return userRemoteDataSource.register(loginRequest)
            }
        }.asLiveData()
    }

    fun login(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>> {
        return object : NetworkOnlyBoundResource<LoginResponse, LoginResponse>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<LoginResponse>> {
                return userRemoteDataSource.login(loginRequest)
            }
        }.asLiveData()
    }

    fun getUserList(page: Int): LiveData<Resource<List<User>>> {

        return object : NetworkBoundResource<List<User>, List<User>>(appExecutors) {

            override fun saveCallResult(item: List<User>) {
                userLocalDataSource.insertUsers(item)
            }

            override fun shouldFetch(data: List<User>?) = data == null || networkUtils.isConnected()

            override fun loadFromDb() = userLocalDataSource.getUserList(page)

            override fun createCall(): LiveData<*> {
                return userRemoteDataSource.getUserList(page)
            }
        }.asLiveData()
    }

    fun getUser(userId: Int): LiveData<Resource<User>> {

        return object : NetworkBoundResource<User, User>(appExecutors) {

            override fun saveCallResult(item: User) {
                userLocalDataSource.insertUser(item)
            }

            override fun shouldFetch(data: User?) = data == null || networkUtils.isConnected()

            override fun loadFromDb() = userLocalDataSource.getUser(userId)

            override fun createCall(): LiveData<*> {
                return userRemoteDataSource.getUser(userId)
            }
        }.asLiveData()
    }


    fun updateUser(user: User, updateUserRequest: UpdateUserRequest): LiveData<Resource<UpdateUserResponse>> {
        return object : NetworkOnlyBoundResource<UpdateUserResponse, UpdateUserResponse>(appExecutors) {

            override fun createCall(): LiveData<ApiResponse<UpdateUserResponse>> {
                return userRemoteDataSource.updateUser(user.id, updateUserRequest)
            }

            override fun makeDBOperation() {
                userLocalDataSource.updateUserOnDatabase(user)
            }
        }.asLiveData()
    }

    fun deleteUser(user: User): LiveData<Resource<Void>> {
        return object : NetworkOnlyBoundResource<Void, Void>(appExecutors) {

            override fun createCall(): LiveData<ApiResponse<Void>> {
                return userRemoteDataSource.deleteUser(user.id)
            }

            override fun makeDBOperation() {
                userLocalDataSource.deleteUserFromDatabase(user)
            }
        }.asLiveData()
    }
}