package com.example.my.springboot

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface AuthApiService {
    @POST("api/user-profiles/register")
    suspend fun register(@Body user: Users): Map<String, Any>

    @POST("api/user-profiles/login")
    suspend fun login(@Body user: Users): Map<String, Any>
}



