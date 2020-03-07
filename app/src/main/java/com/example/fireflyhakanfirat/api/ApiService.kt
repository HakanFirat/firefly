package com.example.fireflyhakanfirat.api

import androidx.lifecycle.LiveData
import com.example.fireflyhakanfirat.api.request.LoginRequest
import com.example.fireflyhakanfirat.api.request.UpdateUserRequest
import com.example.fireflyhakanfirat.api.response.LoginResponse
import com.example.fireflyhakanfirat.api.response.UpdateUserResponse
import com.example.fireflyhakanfirat.data.entity.User
import retrofit2.http.*

/**
 * REST API access points
 */
interface ApiService {

    @POST("api/register")
    fun register(@Body loginRequest: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    @POST("api/login")
    fun login(@Body loginRequest: LoginRequest): LiveData<ApiResponse<LoginResponse>>

    @GET("api/users")
    fun getUserList(@Query("page") page: Int): LiveData<ApiResponse<List<User>>>

    @GET("api/users/{userId}")
    fun getUser(@Path("userId") userId: Int): LiveData<ApiResponse<User>>

    @PUT("api/users/{userId}")
    fun updateUser(@Path("userId") userId: Int, @Body updateUserRequest: UpdateUserRequest): LiveData<ApiResponse<UpdateUserResponse>>

    @PUT("api/users/{userId}")
    fun deleteUser(@Path("userId") userId: Int): LiveData<ApiResponse<Void>>

    /*
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<Repo>>

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RepoSearchResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RepoSearchResponse>

     */
}