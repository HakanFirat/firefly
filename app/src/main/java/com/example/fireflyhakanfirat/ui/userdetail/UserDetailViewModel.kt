package com.example.fireflyhakanfirat.ui.userdetail

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.api.response.UpdateUserResponse
import com.example.fireflyhakanfirat.data.Resource
import com.example.fireflyhakanfirat.data.entity.User
import com.example.fireflyhakanfirat.domain.DeleteUserUseCase
import com.example.fireflyhakanfirat.domain.GetUserUseCase
import com.example.fireflyhakanfirat.domain.UpdateUserUseCase
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase,
                                              private val updateUserUseCase: UpdateUserUseCase,
                                              private val deleteUserUseCase: DeleteUserUseCase): ViewModel() {

    val getUserLiveData = MediatorLiveData<Resource<User>>()
    fun getUser(userId: Int){
        getUserLiveData.addSource(getUserUseCase.getUser(userId)) { value ->
            getUserLiveData.value = value
        }
    }

    val updateUserLiveData = MediatorLiveData<Resource<UpdateUserResponse>>()
    fun updateUser(user: User, updateUserRequest: UpdateUserRequest){
        updateUserLiveData.addSource(updateUserUseCase.updateUser(user, updateUserRequest)) { value ->
            updateUserLiveData.value = value
        }
    }

    val deleteUserLiveData = MediatorLiveData<Resource<Void>>()
    fun deleteUser(user: User){
        deleteUserLiveData.addSource(deleteUserUseCase.deleteUser(user)) { value ->
            deleteUserLiveData.value = value
        }
    }
}