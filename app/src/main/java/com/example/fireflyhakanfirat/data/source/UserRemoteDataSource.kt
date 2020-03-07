package com.example.fireflyhakanfirat.data.source

import com.example.fireflyhakanfirat.api.ApiService
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.data.entity.User
import javax.inject.Inject

class UserRemoteDataSource
@Inject constructor(private val apiService: ApiService): UserDataSource {

    override fun register(loginRequest: LoginRequest) = apiService.register(loginRequest)

    override fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)

    override fun getUserList(page: Int) = apiService.getUserList(page)

    override fun insertUsers(users: List<User>) = throw UnsupportedOperationException()

    override fun getUser(userId: Int) = apiService.getUser(userId)

    override fun insertUser(user: User) = throw UnsupportedOperationException()

    override fun updateUser(userId: Int, updateUserRequest: UpdateUserRequest) = apiService.updateUser(userId, updateUserRequest)

    override fun updateUserOnDatabase(user: User) = throw UnsupportedOperationException()

    override fun deleteUser(userId: Int) = apiService.deleteUser(userId)

    override fun deleteUserFromDatabase(user: User) = throw UnsupportedOperationException()

}