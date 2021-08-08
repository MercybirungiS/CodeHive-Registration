package com.example.codehiverregistration.repository

import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface
import com.example.codehiverregistration.models.LoginRequest
import com.example.codehiverregistration.models.LoginResponse
import com.example.codehiverregistration.models.RegistrationRequest
import com.example.codehiverregistration.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var apiInterface=ApiClient.buildApiClient(ApiInterface::class.java)

//    suspend fun registerStudent(registrationRequest: RegistrationRequest): Response<RegistrationResponse>=
//     withContext(Dispatchers.IO){
//                return@withContext apiInterface.registerStudent(registrationRequest)
//            }
suspend fun registerUser(registrationRequest: RegistrationRequest):
        Response<RegistrationResponse> =
    withContext(Dispatchers.IO) {
        var response = apiInterface.registerStudent(registrationRequest)
        return@withContext response
}
suspend fun loginUser(loginRequest: LoginRequest):
        Response<LoginResponse> =
    withContext(Dispatchers.IO) {
        var response = apiInterface.loginStudent(loginRequest)
        return@withContext response
    }

}