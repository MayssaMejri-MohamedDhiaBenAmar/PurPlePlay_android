package com.example.purpleplay_android.Util

object Constant {

        private const val PORT = "3000"

        /// ----- Emulator ----- ///
      //  const val BASE_URL = "http://10.0.2.2:$PORT/"

        ///
       // const val BASE_URL = "http://127.0.0.1:3000/"


        /// ------ Device ------ ///
        const val BASE_URL = "http://192.168.1.17:$PORT/"
        const val SHARED_PREF_SESSION = "SESSION"


        const val BASE_URL_IMAGES = BASE_URL + "img/"
        const val BASE_URL_VIDEOS = "http://192.168.1.17:3000/movie-files/VALORANT.mp4"
      const val BASE_URL_MUSIC = "http://192.168.1.17:3000/music-files/hateyou.mp3"
}