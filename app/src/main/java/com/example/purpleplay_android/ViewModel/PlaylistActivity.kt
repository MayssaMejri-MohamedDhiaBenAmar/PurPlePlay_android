package com.example.purpleplay_android.ViewModel

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.Model.Music
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Util.Constant
import com.google.android.material.imageview.ShapeableImageView

@Suppress("DEPRECATION")
class PlaylistActivity : AppCompatActivity() {




    //    var albumartIV: ShapeableImageView? = null;
    var playBtn: Button? = null;
    var backBtn: Button? = null;
    var nextBtn: Button? = null;
    var titleTV: TextView? = null;
    lateinit var mediaPlayer: MediaPlayer

    //  var artistTV: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist_layout)

        // initializing our media player
        mediaPlayer = MediaPlayer()
        playBtn = findViewById(R.id.pause)
        titleTV = findViewById(R.id.songname)

        val music = intent.getSerializableExtra("music") as? Music
        titleTV!!.text = music!!.title

      playBtn!!.setOnClickListener {
       //   var audioUrl = "http://192.168.1.17:3000/music-files/hateyou.mp3"

          mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

          // and catch block for our media player.
          try {

              // source as audio url on below line.
              mediaPlayer.setDataSource(Constant.BASE_URL_MUSIC)


              // preparing our media player.
              mediaPlayer.prepare()


              // starting our media player.
              mediaPlayer.start()

          } catch (e: Exception) {

              // on below line we are handling our exception.
              e.printStackTrace()
          }
          // on below line we are displaying a toast message as audio player.
          Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()

      }

    }






}