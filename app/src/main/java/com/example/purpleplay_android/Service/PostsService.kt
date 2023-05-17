package com.example.purpleplay_android.Service

import com.example.purpleplay_android.Model.Post
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsService {

    data class PostResponse(
    @SerializedName("post")
    val post:Post
    )

    data class PostsResponse(
        @SerializedName("posts")
        val posts: List<Post>
    )


    data class AddPost(
        val title: String,
        val description: String
        )

    @POST("/post/")
    fun addPost(@Body postBody: AddPost): Call<PostResponse>


    @GET("post/")
    fun getAll(): Call<PostsResponse>

}