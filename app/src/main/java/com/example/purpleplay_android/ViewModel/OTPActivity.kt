package com.example.purpleplay_android.ViewModel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OTPActivity : AppCompatActivity() {

    private lateinit var pinView: PinView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.otp_layout)
        supportActionBar?.hide()

        pinView=findViewById(R.id.pinView);
        button=findViewById(R.id.confirmOtp);

        button.setOnClickListener {
            ApiService.userService.confOTP(
                UserService.ConfOTPBody(
                    otp = pinView.getText().toString()
                )
            ).enqueue( object : Callback<UserService.UserResponse> {
                override fun onResponse(
                    call: Call<UserService.UserResponse>,
                    response: Response<UserService.UserResponse>
                ) {
                    if (response.code() == 200) {
                        var intent= Intent(this@OTPActivity, ChangePWDActivity::class.java)
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