package com.example.elite_classroom.Retrofit

import com.example.elite_classroom.Models.Retrofit_Models.Auth_Responses
import com.example.elite_classroom.Models.Retrofit_Models.Get_Classes_Response
import com.example.elite_classroom.Models.Retrofit_Models.Google_Logins
import com.example.elite_classroom.Models.Retrofit_Models.Upload_Response
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface DestinationService {


    @POST("users/login")
    fun login_Google_User(@Body Google_Logins: Google_Logins)   : Call<Auth_Responses>

    @GET("getClasses/classes/{google_token}")
    fun get_Classes(@Path("google_token") google_token: String)  : Call<Get_Classes_Response>

    @Multipart
    @POST("storage/upload")
    fun uploadFile(@Part file: MultipartBody.Part?): Call<Upload_Response?>?

}