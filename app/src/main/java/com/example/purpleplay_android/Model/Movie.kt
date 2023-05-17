package com.example.purpleplay_android.Model

import java.io.Serializable
import java.util.*
import kotlin.time.Duration

data class Movie(
    val _id: String,
    val title: String,
    val artist: String,
    val filename: String,
    val imageFilename: String,
    val genre: String,
    val description : String,
    val acteur : String,
    val acteur1 : String,
    val acteur2 : String
) : Serializable
