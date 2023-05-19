package com.example.purpleplay_android.ViewModel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.Fragments.SettingsFragment
import com.example.purpleplay_android.Fragments.TermsAndConditionFragment
import com.example.purpleplay_android.Fragments.homeFragment
import com.example.purpleplay_android.Fragments.updateProfileFragment
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Utils.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val sharedPreferences =
            getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor =
            sharedPreferences.edit()
        sharedPreferencesEditor.clear()
        sharedPreferencesEditor.apply()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation2)
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            println("menuItem.itemId : ${menuItem.itemId}")
            when (menuItem.itemId) {
                R.id.terms -> {
                    // Handle notifications button click
                    val fragment = TermsAndConditionFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.profile -> {
                    // Handle home button click
                    val fragment = updateProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                R.id.settings -> {
                    // Handle dashboard button click
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
                else -> {
                    // Handle notifications button click
                    println("menuItem.itemId : ${menuItem.itemId}")
                    val fragment = TermsAndConditionFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .commit()
                    true
                }
            }
        }
    }
}