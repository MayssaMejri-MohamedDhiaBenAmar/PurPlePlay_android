package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Adapters.AdapterMusic
import com.example.purpleplay_android.Adapters.MusicAdapter
import com.example.purpleplay_android.Models.Music
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.MusicService
import retrofit2.Response

class MusichomeActivity : AppCompatActivity(){

   //VAR

    lateinit var songs: Button
    lateinit var fav: Button
    private lateinit var myAdapter: AdapterMusic



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_layout)
        supportActionBar?.hide()

        fav = findViewById(R.id.fav)
        songs = findViewById(R.id.movies)


        fav.setOnClickListener {
            startActivity(Intent(this@MusichomeActivity, FavoriteActivity::class.java))
        }
        songs.setOnClickListener {
            startActivity(Intent(this@MusichomeActivity, PlaylistActivity::class.java))
        }


        myAdapter = AdapterMusic(emptyList())
        val my_recycler_view: RecyclerView = findViewById(R.id.musicRecycleV)
        my_recycler_view.adapter = myAdapter
        my_recycler_view.layoutManager = LinearLayoutManager(this)



        ApiService.musicService.getAll().enqueue(object  :
            retrofit2.Callback<MusicService.MusicsResponse> {
            override fun onResponse(
                call: retrofit2.Call<MusicService.MusicsResponse>,
                response: Response<MusicService.MusicsResponse>
            ) {
                if (response.code() == 200) {
                    my_recycler_view.adapter =
                        MusicAdapter(response.body()?.musics as MutableList<Music>)

                } else {
                    println("status code is " + response.code())
                }
            }

            override fun onFailure(
                call: retrofit2.Call<MusicService.MusicsResponse>,
                t: Throwable
            ) {
                println("HTTP ERROR")
                t.printStackTrace()
            }


            })}}