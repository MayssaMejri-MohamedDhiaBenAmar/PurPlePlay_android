package com.example.purpleplay_android.Model

import java.io.Serializable
import java.util.*

data class Music(
    val _id: String,
    val title: String,
    val artist: String,
    val filename: String,
    val imagename: String
) : Serializable