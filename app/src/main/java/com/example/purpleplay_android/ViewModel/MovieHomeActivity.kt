package com.example.purpleplay_android.ViewModel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Adapters.AdapterMusic
import com.example.purpleplay_android.Adapters.MovieAdapter
import com.example.purpleplay_android.Model.Movie
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Service.ApiService
import com.example.purpleplay_android.Service.MovieService
import retrofit2.Response

class MovieHomeActivity : AppCompatActivity() {

//lateinit var myAdapter : MovieAdapter
lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: AdapterMusic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_layout)

        myAdapter = AdapterMusic(emptyList())

        recyclerView= findViewById(R.id.movieRecycleV)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        ApiService.movieService.getAll().enqueue (object  :
            retrofit2.Callback<MovieService.MoviesResponse> {
            override fun onResponse(
                call: retrofit2.Call<MovieService.MoviesResponse>,
                response: Response<MovieService.MoviesResponse>
            ) {
                if (response.code() == 200) {
                    recyclerView.adapter =
                        MovieAdapter(response.body()?.movies as MutableList<Movie>)

                } else {
                    println("status code is " + response.code())
                }
            }

            override fun onFailure(
                call: retrofit2.Call<MovieService.MoviesResponse>,
                t: Throwable
            ) {
                println("HTTP ERROR")
                t.printStackTrace()
            }


        })
    }



}
