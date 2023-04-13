package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.R

class Musichome_Activity :AppCompatActivity(){

    //VAR
    private lateinit var songs : Button
    private lateinit var fav : Button

   override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.music_layout)


       // Initializing
       songs =findViewById(R.id.songs)
       fav= findViewById(R.id.fav)


       songs.setOnClickListener{
           val intent = Intent(this,PlaylistActivity::class.java)
           startActivity(intent)
       }

       fav.setOnClickListener{
           val intent = Intent(this@Musichome_Activity,FavoriteActivity::class.java)
           startActivity(intent)
       }
    }

    }