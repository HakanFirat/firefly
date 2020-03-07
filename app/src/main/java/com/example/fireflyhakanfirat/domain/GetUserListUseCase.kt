package com.example.fireflyhakanfirat.domain

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.data.repo.UserRepository
import javax.inject.Inject

interface GetUserListUseCase {
    fun getUserList(page: Int): LiveData<Resource<List<User>>>
}

class GetUserListUseCaseImpl
@Inject constructor(private val userRepository: UserRepository): GetUserListUseCase {
    override fun getUserList(page: Int) = userRepository.getUserList(page)
}