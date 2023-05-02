package com.example.purpleplay_android

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.purpleplay_android.ViewModel.ChatFragment
import com.example.purpleplay_android.ViewModel.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

   // private lateinit var binding: ActivityMainBinding
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       supportActionBar?.hide()
       setFullScreen(this@MainActivity)
       setContentView(R.layout.activity_main)
       val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
       val iconColor = ContextCompat.getColorStateList(this, R.color.purple_app)
       bottomNavigationView.setItemIconTintList(iconColor)
       val bgColor = ContextCompat.getColor(this, R.color.purple_button)
       val textColor = ContextCompat.getColorStateList(this, R.color.purple_app)
       bottomNavigationView.setItemTextColor(textColor)
       bottomNavigationView.setBackground(ColorDrawable(bgColor))
       bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
           when (menuItem.itemId) {
               R.id.home_page -> {
                   val fragment = HomeFragment()
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.fragmentContainerView, fragment)
                       .commit()
                   true
               }
               R.id.chat -> {
                   val fragment = ChatFragment()
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.fragmentContainerView, fragment)
                       .commit()
                   true
               }

               else -> false
           }
       }
   }
    fun setFullScreen(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity.window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}