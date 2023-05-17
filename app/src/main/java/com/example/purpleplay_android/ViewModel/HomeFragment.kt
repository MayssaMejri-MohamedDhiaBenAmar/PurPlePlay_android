package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.purpleplay_android.R

class HomeFragment : Fragment(){
    //VAR
    // private lateinit var binding: ActivityMainBinding
    lateinit var music : Button
    private lateinit var movie : Button
    private lateinit var home : Button
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
        music.setOnClickListener {
            //Log.e("setOnClickListener","CLICKEDDDDDDDDDDDDDDDDDDDd")
            val intent = Intent(requireContext(),MusichomeActivity::class.java)
            startActivity(intent)
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