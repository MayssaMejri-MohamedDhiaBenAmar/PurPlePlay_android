package com.example.purpleplay_android.ViewModel

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
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.MainActivity
import com.example.purpleplay_android.Models.User
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.example.purpleplay_android.Utils.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File

class UpdateProfileActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_profile_layout)

        //Var
        val context = this@UpdateProfileActivity
        //UI Elements
        usernameTIET = findViewById(R.id.usernameTIET)
        passwordTIET = findViewById(R.id.passwordTIET)
        confrimpwdTIET = findViewById(R.id.confirmpasswordTIET)
        mailTIET = findViewById(R.id.mailTIET)
        usernameTIL = findViewById(R.id.usernameTIL)
        passwordTIL = findViewById(R.id.passwordTIL)
        mailTIL = findViewById(R.id.mailTIL)
        confrimpwdTIL = findViewById(R.id.confirmpasswordTIL)
        maleCB = findViewById(R.id.cb_gender_male)
        femaleCB = findViewById(R.id.cb_gender_female)
        UpdateBtn = findViewById(R.id.UpdateBTN)
        PickIMGBtn = findViewById(R.id.PickIMG)
        ProfilePicIV = findViewById(R.id.ProfilePicIV)

        val sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
        val userData = sharedPreferences.getString("USER_DATA", "")
        val user = Gson().fromJson(userData, User::class.java)

        usernameTIET.setText(user.username.toString())
        mailTIET.setText(user.mail.toString())
        if (user.gender == "homme") {
            maleCB.isChecked = true
        } else {
            femaleCB.isChecked = true
        }
        //ProfilePicIV.setImageURI(Uri.parse(user.image))

        UpdateBtn.setOnClickListener {


            val u = sharedPreferences.getString("imgname", "")
            println(Uri.parse(u))
            val resolver = context.contentResolver
            val inputStream = resolver.openInputStream(Uri.parse(u))
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT)
            println("imgname : $u")

            ApiService.userService.updateProfile(
                UserService.UpdateProfileBody(
                    username = usernameTIET.text.toString(),
                    mail = mailTIET.text.toString(),
                    password = passwordTIET.text.toString(),
                    confirmpassword = confrimpwdTIET.text.toString(),
                    gender = "homme",
                    image = u.toString(),
                )
            ).enqueue( object : Callback<UserService.UserResponse> {
                override fun onResponse(
                    call: Call<UserService.UserResponse>,
                    response: Response<UserService.UserResponse>
                ) {
                    if (response.code() == 200) {
                        val json = Gson().toJson(response.body()!!.user)
                        println(json)
                        val sharedPreferences =
                            getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
                        val sharedPreferencesEditor: SharedPreferences.Editor =
                            sharedPreferences.edit()
                        sharedPreferencesEditor.putString("USER_DATA", json)
                        sharedPreferencesEditor.apply()
                        var intent= Intent(this@UpdateProfileActivity, MainActivity::class.java)
                        startActivity(intent)
                        showDialog(context,"Register Successful")
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
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery()
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }


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
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            ProfilePicIV.setImageURI(data?.data)
            println("data?.data : "+data?.data)
            val u: Uri = Uri.parse(data?.data.toString())
            val f = File("" + u)
            println("f.name : "+f.name)
            val sharedPreferences =
                getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
            val sharedPreferencesEditor: SharedPreferences.Editor =
                sharedPreferences.edit()
            sharedPreferencesEditor.putString("imgname", f.name)
            sharedPreferencesEditor.apply()
        }
    }


}