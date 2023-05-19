package com.example.purpleplay_android.Models

import java.io.Serializable
import java.util.*
import kotlin.time.Duration

data class Post(
    val _id: String,
    val description : String
) : Serializable