package com.example.purpleplay_android.ViewModel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.purpleplay_android.Adapters.MusicAdapter
import com.example.purpleplay_android.Models.Music
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.MusicService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MusicFragment : Fragment() {
    private var musicRV: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_music, container, false)

    musicRV = view.findViewById(R.id.musicRV)
    musicRV?.setHasFixedSize(true)

    musicRV!!
        .layoutManager =LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    ApiService.musicService.getAll()
    .enqueue(
    object : Callback<MusicService.MusicsResponse> {
        override fun onResponse(
            call: Call<MusicService.MusicsResponse>,
            response: Response<MusicService.MusicsResponse>
        ) {
            if (response.code() == 200) {
                musicRV!!.adapter =
                    MusicAdapter(response.body()?.musics as MutableList<Music>)

            } else {
                println("status code is " + response.code())
            }
        }

        override fun onFailure(
            call: Call<MusicService.MusicsResponse>,
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