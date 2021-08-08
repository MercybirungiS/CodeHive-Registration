package com.example.codehiverregistration.repository

import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface
import com.example.codehiverregistration.models.CourseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    var apiInterface= ApiClient.buildApiClient(ApiInterface::class.java)
   suspend fun courseResponse(courseResponse: CourseResponse):
           Response<CourseResponse> =
    withContext(Dispatchers.IO) {
        var response = apiInterface.courseResponse(courseResponse)
        return@withContext response
    }
}