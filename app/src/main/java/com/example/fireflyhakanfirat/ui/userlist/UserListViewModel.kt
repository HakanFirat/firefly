package com.example.fireflyhakanfirat.ui.userlist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.domain.GetUserListUseCase
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val getUserListUseCase: GetUserListUseCase): ViewModel() {

    val userListLiveData = MediatorLiveData<Resource<List<User>>>()
    fun getUserList(page: Int){
        userListLiveData.addSource(getUserListUseCase.getUserList(page)) { value ->
            userListLiveData.value = value
        }
    }
}