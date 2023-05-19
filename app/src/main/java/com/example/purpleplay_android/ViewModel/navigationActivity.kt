package com.example.purpleplay_android.ViewModel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.purpleplay_android.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class navigationActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.home_fragment)
        loadFragment(HomeFragment())

        val bottomNav = findViewById<BottomNavigationView>(R.id.bott_nav)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.chat -> {
                    loadFragment(ChatBotFragment())
                    true
                }


                else -> {false}
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}