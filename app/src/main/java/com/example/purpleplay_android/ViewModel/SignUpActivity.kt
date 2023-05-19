package com.example.purpleplay_android.ViewModel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.purpleplay_android.MainActivity
import com.example.purpleplay_android.R
import com.example.purpleplay_android.Services.ApiService
import com.example.purpleplay_android.Services.UserService
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

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
    private lateinit var signUpBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)
        supportActionBar?.hide()

        //Var
        val context = this@SignUpActivity
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
        signUpBtn = findViewById(R.id.SignUpBTN)

        signUpBtn.setOnClickListener {
            if (usernameTIET.text.toString() == "" ||
                mailTIET.text.toString() == "" ||
                passwordTIET.text.toString() == "" ||
                confrimpwdTIET.text.toString() == "") {
                showDialog(context,"Missing Fields ❌")
            } else {
                ApiService.userService.register(
                    UserService.RegisterBody(
                        username = usernameTIET.text.toString(),
                        mail = mailTIET.text.toString(),
                        password = passwordTIET.text.toString(),
                        confirmpassword = confrimpwdTIET.text.toString(),
                        gender = if (maleCB.isChecked) "homme" else "femme",
                        role = "user"
                    )
                ).enqueue( object : Callback<UserService.UserResponse> {
                    override fun onResponse(
                        call: Call<UserService.UserResponse>,
                        response: Response<UserService.UserResponse>
                    ) {
                        if (response.code() == 200) {
                            val json = Gson().toJson(response.body()!!.user)
                            println(json)
                            showDialog(context,"Register Successful")
                            var intent= Intent(this@SignUpActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else if(response.code() == 400) {
                            showDialog(context,"Error ❌")
                        }
                        else if(response.code() == 408) {
                            showDialog(context,"Passwords Don't Match ❌")
                        }
                        else if(response.code() == 409) {
                            showDialog(context,"User Already Exist")
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