package com.example.fireflyhakanfirat.data.source

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.ApiResponse
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.api.response.UpdateUserResponse
import com.example.fireflyhakanfirat.data.entity.User

interface UserDataSource{

    fun register(loginRequest: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    fun login(loginRequest: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    fun getUserList(page: Int): LiveData<*>

    fun insertUsers(users: List<User>)

    fun getUser(userId: Int): LiveData<*>

    fun insertUser(user: User)

    fun updateUser(userId: Int, updateUserRequest: UpdateUserRequest): LiveData<ApiResponse<UpdateUserResponse>>

    fun updateUserOnDatabase(user: User)

    fun deleteUser(userId: Int): LiveData<ApiResponse<Void>>

    fun deleteUserFromDatabase(user: User)

}