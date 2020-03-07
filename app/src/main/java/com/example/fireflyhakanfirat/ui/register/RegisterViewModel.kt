package com.example.fireflyhakanfirat.ui.register

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.domain.RegisterUseCase
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase): ViewModel() {

    val registerLiveData = MediatorLiveData<Resource<LoginResponse>>()
    fun register(loginRequest: LoginRequest){
        registerLiveData.addSource(registerUseCase.register(loginRequest)) { value ->
            registerLiveData.value = value
        }
    }
}