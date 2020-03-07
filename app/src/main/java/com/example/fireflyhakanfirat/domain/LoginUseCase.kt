package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface LoginUseCase {
    fun login(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>>
}

class LoginUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): LoginUseCase {
    override fun login(loginRequest: LoginRequest) = userRepository.login(loginRequest)
}