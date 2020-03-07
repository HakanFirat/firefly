package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface DeleteUserUseCase {
    fun deleteUser(user: User): LiveData<Resource<Void>>
}

class DeleteUserUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): DeleteUserUseCase {
    override fun deleteUser(user: User) = userRepository.deleteUser(user)
}