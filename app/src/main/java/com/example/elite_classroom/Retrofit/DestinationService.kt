package com.example.elite_classroom.Retrofit

import com.example.elite_classroom.Auth_Response
import com.example.elite_classroom.Google_Login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface DestinationService {


    @POST("users/login")
    fun login_Google_User(@Body Google_Login  : Google_Login)   : Call<Auth_Response>
}