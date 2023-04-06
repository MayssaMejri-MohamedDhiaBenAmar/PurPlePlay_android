package com.example.purpleplay.Service

import com.example.purpleplay.Model.User
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface UserService {
    data class MusicResponse(

        val user:User
    )

    data class Login(
        val username: String,
        val password: String
    )

    //@POST("/user/login")
    //fun addMusic(@Body login: Login): Call<LoginResponse>

}