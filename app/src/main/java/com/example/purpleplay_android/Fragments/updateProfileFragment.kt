package com.example.purpleplay_android.Fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.purpleplay_android.MainActivity
import com.example.purpleplay_android.Models.User
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.example.purpleplay_android.Utils.Constant
import com.example.purpleplay_android.ViewModel.UpdateProfileActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var reportBtn : Button
class updateProfileFragment : Fragment() {

    private lateinit var usernameTIET : TextInputEditText
    private lateinit var passwordTIET : TextInputEditText
    private lateinit var confrimpwdTIET : TextInputEditText
    private lateinit var mailTIET : TextInputEditText
    private lateinit var usernameTIL : TextInputLayout
    private lateinit var passwordTIL : TextInputLayout
    private lateinit var confrimpwdTIL : TextInputLayout
    private lateinit var mailTIL : TextInputLayout
    private lateinit var maleCB : CheckBox
    private lateinit var femaleCB : CheckBox
    private lateinit var UpdateBtn : Button
    private lateinit var PickIMGBtn : Button
    private lateinit var ProfilePicIV : ImageView
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)
        //Var
        val context = this@updateProfileFragment
        //UI Elements
        usernameTIET = view.findViewById(R.id.usernameTIET)
        passwordTIET = view.findViewById(R.id.passwordTIET)
        confrimpwdTIET = view.findViewById(R.id.confirmpasswordTIET)
        mailTIET = view.findViewById(R.id.mailTIET)
        usernameTIL = view.findViewById(R.id.usernameTIL)
        passwordTIL = view.findViewById(R.id.passwordTIL)
        mailTIL = view.findViewById(R.id.mailTIL)
        confrimpwdTIL = view.findViewById(R.id.confirmpasswordTIL)
        maleCB = view.findViewById(R.id.cb_gender_male)
        femaleCB = view.findViewById(R.id.cb_gender_female)
        UpdateBtn = view.findViewById(R.id.UpdateBTN)
        PickIMGBtn = view.findViewById(R.id.PickIMG)
        ProfilePicIV = view.findViewById(R.id.ProfilePicIV)

        // Get a value from shared preferences
        val sharedPreferences = this.activity?.getSharedPreferences("SESSION", Context.MODE_PRIVATE)

        if (sharedPreferences != null) {
            val userData = sharedPreferences.getString("USER_DATA", null)
            println("from fragment : "+userData)
            if (userData != null) {
                try {
                    val user = Gson().fromJson(userData.toString(), User::class.java)

                    if (user != null) {
                        usernameTIET.setText(user.username.toString())
                        mailTIET.setText(user.mail.toString())
                        if (user.gender == "homme") {
                            maleCB.isChecked = true
                        } else {
                            femaleCB.isChecked = true
                        }
                        //ProfilePicIV.setImageURI(Uri.parse(user.image))
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
            //println(user.username)
        }




        UpdateBtn.setOnClickListener {


            /*
            val u = sharedPreferences.getString("imgname", "")
            println(Uri.parse(u))
            val resolver = view.context.contentResolver
            val inputStream = resolver.openInputStream(Uri.parse(u))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)
            println("imgname : $u")
            */

            ApiService.userService.updateProfile(
                UserService.UpdateProfileBody(
                    username = usernameTIET.text.toString(),
                    mail = mailTIET.text.toString(),
                    password = passwordTIET.text.toString(),
                    confirmpassword = confrimpwdTIET.text.toString(),
                    gender = "homme",
                    image = "dhia.jpg",
                )
            ).enqueue( object : Callback<UserService.UserResponse> {
                override fun onResponse(
                    call: Call<UserService.UserResponse>,
                    response: Response<UserService.UserResponse>
                ) {
                    if (response.code() == 200) {
                        val json = Gson().toJson(response.body()!!.user)

                        val sharedPreferences =
                            view.context.getSharedPreferences(
                                Constant.SHARED_PREF_SESSION,
                                AppCompatActivity.MODE_PRIVATE
                            )
                        val sharedPreferencesEditor: SharedPreferences.Editor =
                            sharedPreferences.edit()
                        sharedPreferencesEditor.putString("USER_DATA", json)
                        sharedPreferencesEditor.apply()
                        showDialog(view.context,"Update Successful")
                    }
                }

                override fun onFailure(call: Call<UserService.UserResponse>, t: Throwable) {
                    println("HTTP ERROR")
                    t.printStackTrace()
                }

            } )
        }

        PickIMGBtn.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                //show popup to request runtime permission
                requestPermissions(permissions, 1001);
                //permission already granted
                pickImageFromGallery()
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        return view
    }

    fun showDialog(activityName: Context, message:String){
        val builder = AlertDialog.Builder(activityName)
        builder.setTitle("Caution ⚠️")
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        //Permission code
        private val PERMISSION_CODE = 1001
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied

                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            ProfilePicIV.setImageURI(data?.data)
            val u: Uri = Uri.parse(data?.data.toString())
            val f = File("" + u)
            val sharedPreferences =
                view?.context?.getSharedPreferences(Constant.SHARED_PREF_SESSION, AppCompatActivity.MODE_PRIVATE)
            val sharedPreferencesEditor: SharedPreferences.Editor? =
                sharedPreferences?.edit()
            if (sharedPreferencesEditor != null) {
                sharedPreferencesEditor.putString("imgname", f.name)
            }
            if (sharedPreferencesEditor != null) {
                sharedPreferencesEditor.apply()
            }
        }
    }

}