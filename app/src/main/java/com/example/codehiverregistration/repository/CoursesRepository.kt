package com.example.codehiverregistration.repository

import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface
import com.example.codehiverregistration.models.CourseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import com.example.codehiverregistration.models.*

class CoursesRepository {

    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun courses(accessToken:String): Response<List<CourseResponse>> = withContext(Dispatchers.IO){
            var response=apiInterface.getCourses(accessToken)
            return@withContext response

        }

    suspend fun enrollment (accessToken:String): Response<EnrollmentResponse> = withContext(Dispatchers.IO){
            var enrol=apiInterface.getEnrolment(accessToken)
            return@withContext enrol
    }

}