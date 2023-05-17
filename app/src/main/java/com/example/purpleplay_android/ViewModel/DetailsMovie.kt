package com.example.purpleplay_android.ViewModel

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.purpleplay_android.Model.Movie
import com.example.purpleplay_android.Model.Music
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Util.Constant

class DetailsMovie  : AppCompatActivity(){

    var titlee: TextView?= null;
    var time: TextView? = null;
    var btn:Button?= null;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_movie)

        // initializing our media player
        titlee = findViewById(R.id.textView)
        time = findViewById(R.id.time)
        val desc: TextView = findViewById(R.id.textView3)

         btn = findViewById(R.id.watchnow)
        val movie = intent.getSerializableExtra("movie") as? Movie
        titlee!!.text = movie!!.title
        desc.text = movie!!.description

        val img: ImageView = findViewById(R.id.imageView4)
        val actor: ImageView = findViewById(R.id.act1)
        val actor1: ImageView = findViewById(R.id.act2)

        Glide.with(this)
            .load(movie!!.imageFilename)
            .apply(RequestOptions().placeholder(R.drawable.playlist).centerCrop())
            .into(img)
        Glide.with(this)
            .load(movie!!.acteur)
            .apply(RequestOptions().placeholder(R.drawable.playlist).centerCrop())
            .into(actor)
        Glide.with(this)
            .load(movie!!.acteur1)
            .apply(RequestOptions().placeholder(R.drawable.playlist).centerCrop())
            .into(actor1)

        btn!!.setOnClickListener {
            //Log.e("setOnClickListener","CLICKEDDDDDDDDDDDDDDDDDDDd")
            val intent = Intent(this,PlayMovieActivity::class.java)
            startActivity(intent)
        }

    }



}

