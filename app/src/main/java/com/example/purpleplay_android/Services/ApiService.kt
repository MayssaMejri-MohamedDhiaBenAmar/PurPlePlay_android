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
    val musicService: MusicService by lazy {
        retrofit().create(MusicService::class.java)
    }

    val movieService: MovieService by lazy {
        retrofit().create(MovieService::class.java)
    }

    val postService: PostsService by lazy {
        retrofit().create(PostsService::class.java)
    }
}