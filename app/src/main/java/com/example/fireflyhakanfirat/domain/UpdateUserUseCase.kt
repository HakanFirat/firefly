package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.api.response.UpdateUserResponse
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface UpdateUserUseCase {
    fun updateUser(user: User, updateUserRequest: UpdateUserRequest): LiveData<Resource<UpdateUserResponse>>
}

class UpdateUserUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): UpdateUserUseCase {
    override fun updateUser(user: User, updateUserRequest: UpdateUserRequest) = userRepository.updateUser(user, updateUserRequest)
}