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
import com.example.purpleplay_android.ViewModel.*
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
        supportActionBar?.hide()

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
            if (usernameTIET.text.toString() == "" || passwordTIET.text.toString() == "") {
                showDialog(context,"Missing Fields ❌")
            } else {


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
                            val sharedPreferences =
                                getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
                            val sharedPreferencesEditor: SharedPreferences.Editor =
                                sharedPreferences.edit()
                            val json = Gson().toJson(response.body()!!.user)
                            sharedPreferencesEditor.putString("USER_DATA", json)
                            sharedPreferencesEditor.apply()
                            var intent= Intent(this@MainActivity, MainActivityBeta::class.java)
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