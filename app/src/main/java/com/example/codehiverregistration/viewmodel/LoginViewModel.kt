package com.example.codehiverregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehiverregistration.models.LoginRequest
import com.example.codehiverregistration.models.LoginResponse
import com.example.codehiverregistration.models.RegistrationRequest
import com.example.codehiverregistration.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    var loginLiveData=MutableLiveData<LoginResponse>()
    var loginFailLiveData=MutableLiveData<String>()
    var userRepository= UserRepository()

    fun logIn(loginRequest: LoginRequest){
        viewModelScope.launch {
            var response=userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                loginFailLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

