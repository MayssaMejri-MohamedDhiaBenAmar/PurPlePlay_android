package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.example.purpleplay_android.Utils.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertEmailActivity : AppCompatActivity() {

    private lateinit var mailTIL : TextInputLayout
    private lateinit var mailTIET : TextInputEditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpwd_layout)
        supportActionBar?.hide()

        mailTIL = findViewById(R.id.lyt_email)
        mailTIET = findViewById(R.id.et_email)
        button = findViewById(R.id.btnNext)

        button.setOnClickListener {
            ApiService.userService.forgotPWD(
                UserService.ForgotPWDBody(
                    mail = mailTIET.text.toString()
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
                        sharedPreferencesEditor.putString("email", mailTIET.text.toString())
                        sharedPreferencesEditor.apply()
                        var intent= Intent(this@InsertEmailActivity, OTPActivity::class.java)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<UserService.UserResponse>, t: Throwable) {
                    println("HTTP ERROR")
                    t.printStackTrace()
                }
            } )
        }

    }
}