package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.purpleplay_android.Model.Music
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Util.Constant

@Suppress("DEPRECATION")
class PlaylistActivity : AppCompatActivity() {




    //    var albumartIV: ShapeableImageView? = null;
    var playBtn: ImageView? = null;
    var backBtn: ImageView? = null;
    var nextBtn: ImageView? = null;
    var titleTV: TextView? = null;
    var button1: Button? = null
    lateinit var mediaPlayer: MediaPlayer

    //  var artistTV: i? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playlist_layout)

        // initializing our media player
        mediaPlayer = MediaPlayer()
        playBtn = findViewById(R.id.pause)
        titleTV = findViewById(R.id.songname)
        nextBtn = findViewById(R.id.skip)
        backBtn = findViewById(R.id.skipback)
//        button1 = findViewById(R.id.back);


      /*  button1!!.setOnClickListener {
            val intent = Intent(this@PlaylistActivity,MusichomeActivity::class.java)
            startActivity(intent)
        }*/
        // setting onClickListener

        val music = intent.getSerializableExtra("music") as? Music
        titleTV!!.text = music!!.title

        /*  playBtn!!.setOnClickListener {
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

      }*/


        /// Play and Pause button
        playBtn!!.setOnClickListener {
            if (mediaPlayer.isPlaying) {

                mediaPlayer.pause()
                playBtn!!.setImageResource(R.drawable.play)
                Toast.makeText(applicationContext, "Audio paused..", Toast.LENGTH_SHORT).show()
            } else {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                try {
                    mediaPlayer.setDataSource(Constant.BASE_URL_MUSIC)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    playBtn!!.setImageResource(R.drawable.pause)
                    Toast.makeText(
                        applicationContext,
                        "Audio started playing..",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        var currentMusicIndex = 0
           var audioUrl = "http://192.168.1.17:3000/music-files/escape.mp3"


        // Back button
        backBtn!!.setOnClickListener {
            if (currentMusicIndex > 0) {
                currentMusicIndex--
                playMusic(audioUrl[currentMusicIndex])
            }
            // Next button
            nextBtn!!.setOnClickListener {
                if (currentMusicIndex < Constant.BASE_URL_MUSIC.length - 1) {
                    currentMusicIndex++
                    playMusic(audioUrl[currentMusicIndex])
                }
            }
        }
    }


     fun playMusic(fileName: Char) {
        mediaPlayer.reset()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer.setDataSource(Constant.BASE_URL_MUSIC)
            mediaPlayer.prepare()
            mediaPlayer.start()
            playBtn!!.setImageResource(R.drawable.pause)
            titleTV!!.text = title // update the title with the name of the current playing music
            Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}