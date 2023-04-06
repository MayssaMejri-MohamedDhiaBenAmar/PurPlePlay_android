package com.example.purpleplay_android.Services

import com.example.purpleplay_android.Utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }
    val userService: UserService by lazy {
        retrofit().create(UserService::class.java)
    }
}