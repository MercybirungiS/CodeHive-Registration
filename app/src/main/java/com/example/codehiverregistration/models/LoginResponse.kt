package com.example.codehiverregistration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message: String,
    @SerializedName("student_id") var student_id:String,
    @SerializedName("access_token")var token: String
)
