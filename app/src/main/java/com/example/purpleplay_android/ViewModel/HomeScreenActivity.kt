package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.purpleplay_android.R
import com.example.purpleplay_android.databinding.ActivityMainBinding

class HomeScreenActivity : AppCompatActivity() {
    //VAR
     private lateinit var binding: ActivityMainBinding
    private lateinit var music : Button
    private lateinit var movie : Button
    private lateinit var home : Button
    private lateinit var toggle: ActionBarDrawerToggle


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestRuntimePermission()
        setContentView(R.layout.homescreen_layout)

       // binding = ActivityMainBinding.inflate(layoutInflater)
       // setContentView(binding.root)
        //for nav drawer

       // toggle = ActionBarDrawerToggle(this,binding.root,R.string.open,R.string.close)


        // Initializing  UI ELEMENTS
        music =findViewById(R.id.musicbtn)
        movie= findViewById(R.id.moviebtn)
        home = findViewById(R.id.homebtn)



        music.setOnClickListener { val intent = Intent(this,Musichome_Activity::class.java) }
            }

    //for requesting permission
    private fun requestRuntimePermission(){
    if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
    }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted" ,Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)

        }

    }
}