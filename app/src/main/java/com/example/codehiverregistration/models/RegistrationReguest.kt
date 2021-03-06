package com.example.codehiverregistration.models

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    var name: String,
    @SerializedName("phone_number") var phoneNumber: String,
    var email: String,
    @SerializedName("date_of_birth") var dateOfBirth: String,
    var password: String,
    @SerializedName("nationality") var nationality:String
)
