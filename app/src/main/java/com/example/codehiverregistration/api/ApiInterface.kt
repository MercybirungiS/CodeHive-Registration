package com.example.codehiverregistration.api

import com.example.codehiverregistration.models.*

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    suspend fun registerStudent(@Body registrationRequest: RegistrationRequest) : Response<RegistrationResponse>
    @POST("/students/login")
    suspend fun loginStudent(@Body loginRequest:LoginRequest):Response<LoginResponse>
    @GET("/courses")
    suspend fun getCourses(@Header ("Authorization") token: String):Response<List<CourseResponse>>
    @POST("enrolments")
    suspend fun getEnrolment(@Header("Authorization")token: String):Response<EnrollmentResponse>
}