package com.example.purpleplay_android.Services

import com.example.purpleplay_android.Models.Movie
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieService {

    data class MovieResponse(
    @SerializedName("movie")
    val movie:Movie
    )

    data class MoviesResponse(
        @SerializedName("movies")
        val movies: List<Movie>
    )





    @GET("movie/getALL")
    fun getAll(): Call<MoviesResponse>

}