package com.example.elite_classroom.Retrofit

import com.example.elite_classroom.Models.Retrofit_Models.Auth_Responses
import com.example.elite_classroom.Models.Retrofit_Models.Get_Classes_Response
import com.example.elite_classroom.Models.Retrofit_Models.Google_Logins
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface DestinationService {


    @POST("users/login")
    fun login_Google_User(@Body Google_Logins  : Google_Logins)   : Call<Auth_Responses>

    @GET("getClasses/classes/{google_token}")
    fun get_Classes(@Path("google_token") google_token : String)  : Call<Get_Classes_Response>
}