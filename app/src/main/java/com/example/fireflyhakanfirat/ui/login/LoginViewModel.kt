package com.example.fireflyhakanfirat.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.domain.LoginUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {


    val loginLiveData = MediatorLiveData<Resource<LoginResponse>>()
    fun login(loginRequest: LoginRequest){
        loginLiveData.addSource(loginUseCase.login(loginRequest)) { value ->
            loginLiveData.value = value
        }
    }

}