package com.example.purpleplay_android.ViewModel

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.purpleplay_android.Models.User
import com.example.purpleplay_android.R
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class HomeFragment : Fragment(){
    //VAR
    // private lateinit var binding: ActivityMainBinding
    lateinit var music : Button
    private lateinit var movie : Button
    private lateinit var home : Button
    private lateinit var welcomeText : TextView
    // private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.homescreen_layout, container, false)
        // Initializing  UI ELEMENTS
        music =view.findViewById(R.id.musicbtn)
        movie= view.findViewById(R.id.moviebtn)
        home = view.findViewById(R.id.homebtn)
        welcomeText = view.findViewById(R.id.welcome)
        music.setOnClickListener {
            //Log.e("setOnClickListener","CLICKEDDDDDDDDDDDDDDDDDDDd")
            val intent = Intent(requireContext(),MusichomeActivity::class.java)
            startActivity(intent)
        }

        // Get a value from shared preferences
        val sharedPreferences = this.activity?.getSharedPreferences("SESSION", Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            val userData = sharedPreferences.getString("USER_DATA", null)
            println("from fragment : " + userData)
            if (userData != null) {
                try {
                    val user = Gson().fromJson(userData.toString(), User::class.java)

                    if (user != null) {
                        welcomeText.setText("Welcome "+user.username.toString())
                    }
                } catch (e: JsonSyntaxException) {
                    // Handle JSON syntax error
                    e.printStackTrace()
                } catch (e: Exception) {
                    // Handle other errors
                    e.printStackTrace()
                }
            } else {
                // Handle null value for "USER_DATA" key
                println("USER_DATA is null")
            }
        }

        movie.setOnClickListener {
            //Log.e("setOnClickListener","CLICKEDDDDDDDDDDDDDDDDDDDd")
            val intent = Intent(requireContext(),MovieHomeActivity::class.java)
            startActivity(intent)
        }
        return view
    }
    //For requesting permission
    private fun requestRuntimePermission(){
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)
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
                Toast.makeText(requireContext(),"Permission Granted" , Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),13)

        }

    }

}