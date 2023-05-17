package com.example.purpleplay_android.ViewModel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Adapters.PostAdapter
import com.example.purpleplay_android.Model.Post
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Service.ApiService
import com.example.purpleplay_android.Service.PostsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : Fragment() {
    private var postRV: RecyclerView? = null
    var btn_send : Button? = null
    var msg : EditText? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.posts_layout, container, false)
        // Initializing  UI ELEMENTS
        postRV = view.findViewById(R.id.recyclerView)
        postRV?.setHasFixedSize(true)
        btn_send = view.findViewById(R.id.btnSend)
        msg = view.findViewById(R.id.message)
        postRV!!
            .layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        ApiService.postService.getAll()
            .enqueue(
                object : Callback<PostsService.PostsResponse> {
                    override fun onResponse(
                        call: Call<PostsService.PostsResponse>,
                        response: Response<PostsService.PostsResponse>
                    ) {
                        if (response.code() == 200) {
                            postRV!!.adapter =
                                PostAdapter(response.body()?.posts as MutableList<Post>)
                        } else {
                            println("status code is " + response.code())
                        }
                    }

                    override fun onFailure(
                        call: Call<PostsService.PostsResponse>,
                        t: Throwable
                    ) {
                        println("HTTP ERROR")
                        t.printStackTrace()
                    }

                }
            )




        return view
    }
}