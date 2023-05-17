package com.example.purpleplay_android.ViewModel

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Service.ApiService
import com.example.purpleplay_android.Service.UserService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameTIET : TextInputEditText
    private lateinit var passwordTIET : TextInputEditText
    private lateinit var usernameTIL : TextInputLayout
    private lateinit var passwordTIL : TextInputLayout
    private lateinit var rememberMe : CheckBox
    private lateinit var signInBtn : Button
    private lateinit var signUpBtn : LinearLayoutCompat
    private lateinit var forgetPwd : LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.login_layout)
        //Var
        val context = this@LoginActivity
        //UI Elements
        usernameTIET = findViewById(R.id.usernameTIET)
        passwordTIET = findViewById(R.id.passwordTIET)
        usernameTIL = findViewById(R.id.usernameTIL)
        passwordTIL = findViewById(R.id.passwordTIL)
        rememberMe = findViewById(R.id.cb_rm)
        signInBtn = findViewById(R.id.SignInBTN)
        signUpBtn = findViewById(R.id.signup)
        forgetPwd = findViewById(R.id.forgotPwd)

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
                        showDialog(context,"Login Successful")
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

    fun showDialog(activityName: Context, message:String){
        val builder = AlertDialog.Builder(activityName)
        builder.setTitle("Caution ⚠️")
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)
        val dialog = builder.create()
        dialog.show()
    }

}