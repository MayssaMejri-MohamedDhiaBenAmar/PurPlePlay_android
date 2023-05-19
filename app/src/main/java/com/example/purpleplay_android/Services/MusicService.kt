package com.example.purpleplay_android.Services

import com.example.purpleplay_android.Models.Music
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MusicService {

    data class MusicResponse(
    @SerializedName("music")
    val music:Music
    )

    data class MusicsResponse(
        @SerializedName("musics")
        val musics: List<Music>
    )


    data class AddMusic(
        val title: String,
        val filename: String,
        val imagename: String
        )
    data class UpdateMusic(
        val title: String,
        val filename: String,
        val imagename: String
    )


    @POST("music/add")
    fun addMusic(@Body addMusic: AddMusic): Call<MusicResponse>

    @POST("music/update")
    fun update(@Body updateMusic: UpdateMusic): Call<MusicResponse>


    @GET("music/get")
    fun getAll(): Call<MusicsResponse>

}