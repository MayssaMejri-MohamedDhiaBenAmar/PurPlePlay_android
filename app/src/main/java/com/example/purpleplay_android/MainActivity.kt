package com.example.purpleplay_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.purpleplay_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   // private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen_layout)
    }
}