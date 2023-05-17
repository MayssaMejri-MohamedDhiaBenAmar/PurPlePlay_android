package com.example.purpleplay_android.Service

import com.example.purpleplay_android.Model.User
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    data class UserResponse(
        @SerializedName("user")
        val user: User,
        @SerializedName("message")
        val message: String,
        @SerializedName("token")
        val token: String
    )

    data class ImageData(
        @SerializedName("image") val image: String
    )

    data class RegisterBody(
        val username : String,
        val mail : String,
        val password : String,
        val confirmpassword : String,
        val gender : String,
        val role : String
    )

    data class SendConfMailBody(
        val mail : String
    )

    data class LoginBody(
        val username: String,
        val password: String
    )

    data class ForgotPWDBody(
        val mail: String
    )

    data class UpdateProfileBody(
        val username : String,
        val mail : String,
        val password : String,
        val confirmpassword : String,
        val gender : String,
        @SerializedName("image") val image: String,
    )

    data class UpdatePWDBody(
        val newPassword : String,
        val mail: String
    )

    data class ConfOTPBody(
        val otp : String
    )

    data class GetUserFrowJWTBody(
        val token : String
    )

    @POST("/user/register")
    fun register(@Body registerBody : RegisterBody) : Call<UserResponse>

    @POST("user/login")
    fun login(@Body loginBody : LoginBody) : Call<UserResponse>

    @POST("/user/forgot-password")
    fun forgotPWD(@Body forgotPWDBody: ForgotPWDBody) : Call<UserResponse>

    @POST("/user/confirmationOtp")
    fun confOTP(@Body confOTPBody: ConfOTPBody) : Call<UserResponse>

    @PUT("/user/update-password")
    fun updatePWD(@Body updatePWDBody: UpdatePWDBody) : Call<UserResponse>

    @PUT("/user/update-profile")
    fun updateProfile(@Body updateProfileBody: UpdateProfileBody) : Call<UserResponse>

    @POST("/user/send-confirmation-email")
    fun sendConfEmail(@Body sendConfMailBody: SendConfMailBody) : Call<UserResponse>

    @POST("/user/getUserFrowJWT")
    fun getUserFrowJWT(@Body getUserFrowJWTBody: GetUserFrowJWTBody) : Call<UserResponse>

    @Multipart
    @POST("uploadImage")
    fun uploadImage(@Part("image") image: ImageData): Call<ResponseBody>

    @GET("/confirmation/{token}")
    fun confirmation(@Path("token") token: String): Call<ResponseBody>

}