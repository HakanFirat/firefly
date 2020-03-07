package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface RegisterUseCase {
    fun register(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>>
}

class RegisterUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): RegisterUseCase {
    override fun register(loginRequest: LoginRequest) = userRepository.register(loginRequest)
}