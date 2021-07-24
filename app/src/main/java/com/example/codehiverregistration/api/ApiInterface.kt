package com.example.codehiverregistration.api

import com.example.codehiverregistration.modules.LoginRequest
import com.example.codehiverregistration.modules.LoginResponse
import com.example.codehiverregistration.modules.RegistrationRequest
import com.example.codehiverregistration.modules.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest) : Call<RegistrationResponse>
    @POST("/students/login")
    fun loginStudent(@Body loginRequest:LoginRequest):Call<LoginResponse>
}