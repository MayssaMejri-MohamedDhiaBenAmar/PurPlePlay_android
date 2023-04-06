package com.example.purpleplay_android

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.purpleplay_android.Models.User
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.example.purpleplay_android.Utils.Constant
import com.example.purpleplay_android.ViewModel.HomePageActivity
import com.example.purpleplay_android.ViewModel.InsertEmailActivity
import com.example.purpleplay_android.ViewModel.SignUpActivity
import com.example.purpleplay_android.ViewModel.UpdateProfileActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var usernameTIET : TextInputEditText
    private lateinit var passwordTIET : TextInputEditText
    private lateinit var usernameTIL : TextInputLayout
    private lateinit var passwordTIL : TextInputLayout
    private lateinit var rememberMe : CheckBox
    private lateinit var signInBtn : Button
    private lateinit var signUpBtn : LinearLayoutCompat
    private lateinit var forgetPwd : LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        //Var
        val context = this@MainActivity
        //UI Elements
        usernameTIET = findViewById(R.id.usernameTIET)
        passwordTIET = findViewById(R.id.passwordTIET)
        usernameTIL = findViewById(R.id.usernameTIL)
        passwordTIL = findViewById(R.id.passwordTIL)
        rememberMe = findViewById(R.id.cb_rm)
        signInBtn = findViewById(R.id.SignInBTN)
        signUpBtn = findViewById(R.id.signup)
        forgetPwd = findViewById(R.id.forgotPwd)

        forgetPwd.setOnClickListener{
            var intent= Intent(this, InsertEmailActivity::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {

            ApiService.userService.login(
                UserService.LoginBody(
                    username = usernameTIET.text.toString(),
                    password = passwordTIET.text.toString()
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
                        val editor = sharedPreferences.edit()
                        editor.putString("USER_DATA", "test Sting")
                        val result = editor.commit()
                        if (!result) {
                            println("Failed to save USER_DATA to shared preferences")
                        }
                        editor.apply()
                        showDialog(context,"Login Successful")
                        val userData = sharedPreferences.getString("USER_DATA", "")
                        //val user = Gson().fromJson(userData, User::class.java)
                        //println(user != null)
                        println(userData)
                        var intent= Intent(this@MainActivity, HomePageActivity::class.java)
                        startActivity(intent)
                    }
                    else if(response.code() == 403) {
                        showDialog(context,"No User Found")
                    }
                    else if(response.code() == 400) {
                        showDialog(context,"Wrong password ❌")
                    }
                    else if(response.code() == 402) {
                        showDialog(context,"Missing Fields ❌")
                    }
                    else if(response.code() == 406) {
                        showDialog(context,"Please Verify Your Account")
                    }
                    else {
                        showDialog(context,"Missing Fields ❌")
                    }
                }

                override fun onFailure(call: Call<UserService.UserResponse>, t: Throwable) {
                    println("HTTP ERROR")
                    t.printStackTrace()
                }

            } )

        }

        signUpBtn.setOnClickListener {
            var intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
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


}