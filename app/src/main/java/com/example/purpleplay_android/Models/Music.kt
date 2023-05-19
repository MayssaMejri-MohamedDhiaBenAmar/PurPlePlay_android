package com.example.purpleplay_android.Models

import java.io.Serializable
import java.util.*
import kotlin.time.Duration

data class Music(
    val _id: String,
    val title: String,
    val artist: String,
    val filename: String,
    val imagename: String,
    val duration: Long =0
) : Serializable