package com.example.purpleplay_android.ViewModel;

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.MainActivity
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.example.purpleplay_android.Utils.Constant
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePWDActivity : AppCompatActivity() {

    private lateinit var pwdTIL : TextInputLayout
    private lateinit var pwdTIET : TextInputEditText
    private lateinit var confpwdTIL : TextInputLayout
    private lateinit var confpwdTIET : TextInputEditText
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password_layout)
        supportActionBar?.hide()

        pwdTIL = findViewById(R.id.lyt_cPwd)
        pwdTIET =findViewById(R.id.et_cPwd)
        confpwdTIL = findViewById(R.id.lyt_CcPwd)
        confpwdTIET =findViewById(R.id.et_CcPwd)
        button = findViewById(R.id.btnNext)

        button.setOnClickListener {
            val sharedPreferences = this@ChangePWDActivity.getSharedPreferences(Constant.SHARED_PREF_SESSION, MODE_PRIVATE)
            val mail = sharedPreferences.getString("email", "")
            ApiService.userService.updatePWD(
                UserService.UpdatePWDBody(
                    mail = mail.toString(),
                    newPassword = pwdTIET.text.toString()
                )
            ).enqueue( object : Callback<UserService.UserResponse> {
                override fun onResponse(
                    call: Call<UserService.UserResponse>,
                    response: Response<UserService.UserResponse>
                ) {
                    if (response.code() == 200) {
                        var intent= Intent(this@ChangePWDActivity, MainActivity::class.java)
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
