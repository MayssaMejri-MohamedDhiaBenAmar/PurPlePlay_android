package com.example.purpleplay_android.Model

import java.io.Serializable

data class User (

    val _id: String,
    val username: String,
    val gender: String,
    val password: String,
    val mail: String,
    val image: String,
    val role: String,
    val verified: String,
    val otp: String

) : Serializable