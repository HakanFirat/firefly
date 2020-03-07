package com.example.fireflyhakanfirat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(@PrimaryKey val id: Int = 0,
                val email: String? = "",
                @SerializedName("first_name") val firstname: String? = "",
                @SerializedName("last_name") val lastname: String? = "",
                val avatar: String? = "")