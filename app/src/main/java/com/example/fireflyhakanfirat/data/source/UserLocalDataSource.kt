package com.example.fireflyhakanfirat.data.source

import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.data.db.UserDao
import com.example.fireflyhakanfirat.data.entity.User
import javax.inject.Inject

class UserLocalDataSource
@Inject constructor(private val userDao: UserDao): UserDataSource {

    override fun register(loginRequest: LoginRequest) = throw UnsupportedOperationException()

    override fun login(loginRequest: LoginRequest) = throw UnsupportedOperationException()

    override fun getUserList(page: Int) = userDao.getUsers()

    override fun insertUsers(users: List<User>){ userDao.insertUsers(users) }

    override fun getUser(userId: Int) = userDao.getUser(userId)

    override fun insertUser(user: User) { userDao.insertUser(user) }

    override fun updateUser(userId: Int, updateUserRequest: UpdateUserRequest) = throw UnsupportedOperationException()

    override fun updateUserOnDatabase(user: User) { userDao.deleteUser(user) }

    override fun deleteUser(userId: Int) = throw UnsupportedOperationException()

    override fun deleteUserFromDatabase(user: User) { userDao.deleteUser(user) }

}
