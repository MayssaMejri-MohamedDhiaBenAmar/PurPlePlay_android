package com.example.purpleplay_android.Service

import com.example.purpleplay_android.Util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }


    val musicService: MusicService by lazy {
        retrofit().create(MusicService::class.java)
    }

    val movieService: MovieService by lazy {
        retrofit().create(MovieService::class.java)
    }
}