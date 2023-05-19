package com.example.purpleplay_android.ViewModel

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Utils.Constant

class PlayMovieActivity : AppCompatActivity() {



    // a variable for our video view.
    lateinit var videoView: VideoView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playmovie_layout)
        supportActionBar?.hide()

        // initializing our buttons with id.
        videoView = findViewById(R.id.videoView);

        //creating uri for our video view.
        val uri: Uri = Uri.parse(Constant.BASE_URL_VIDEOS)


        // setting video uri for our video view.
        videoView.setVideoURI(uri)

        // variable for media controller and initializing it.
        val mediaController = MediaController(this)

         // view for our media controller.
        mediaController.setAnchorView(videoView)

        // on below line we are setting media player
        // for our media controller.
        mediaController.setMediaPlayer(videoView)

        // on below line we are setting media
        // controller for our video view.
        videoView.setMediaController(mediaController)

        // on below line we are
        // simply starting our video view.
        videoView.start()

    }
}




