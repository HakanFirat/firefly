package com.example.fireflyhakanfirat.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fireflyhakanfirat.data.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>)

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUser(userId: Int?): LiveData<User>

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<User>>

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}