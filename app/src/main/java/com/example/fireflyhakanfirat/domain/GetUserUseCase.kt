package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface GetUserUseCase {
    fun getUser(userId: Int): LiveData<Resource<User>>
}

class GetUserUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): GetUserUseCase {
    override fun getUser(userId: Int): LiveData<Resource<User>> = userRepository.getUser(userId)
}