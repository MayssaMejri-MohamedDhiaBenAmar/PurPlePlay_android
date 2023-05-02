package com.example.purpleplay_android.Model

import java.io.Serializable
import java.util.*
import kotlin.time.Duration

data class Movie(
    val _id: String,
    val title: String,
    val artist: String,
    val filename: String,
    val imagename: String,
    val genre: String,
    val description : String
    ) : Serializable
