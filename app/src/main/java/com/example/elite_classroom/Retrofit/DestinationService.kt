package com.example.elite_classroom.Retrofit

import com.example.elite_classroom.Models.Recycler_Models.Class_Fixtures
import com.example.elite_classroom.Models.Retrofit_Models.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface DestinationService {


    @POST("users/login")
    fun login_Google_User(@Body Google_Logins: Google_Logins): Call<Auth_Responses>

    @GET("getClasses/classes/{google_token}")
    fun get_Classes(@Path("google_token") google_token: String): Call<Get_Classes_Response>

    @Multipart
    @POST("storage/upload")
    fun uploadFile(@Part file: MultipartBody.Part?): Call<Upload_Response?>?

    @GET("weekly-calender/getCalender-thisWeek/{google_token}")
    fun current_calender(@Path("google_token") google_token: String): Call<ArrayList<Class_Fixtures>>

    @GET("weekly-calender/getCalender-nextWeek/{google_token}")
    fun next_calender(@Path("google_token") google_token: String): Call<ArrayList<Class_Fixtures>>

}