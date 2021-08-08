package com.example.codehiverregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehiverregistration.models.RegistrationRequest
import com.example.codehiverregistration.models.RegistrationResponse
import com.example.codehiverregistration.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    var registrationLiveData=MutableLiveData<RegistrationResponse>()
    var registrationFailLiveData=MutableLiveData<String>()
    var userRepository=UserRepository()

    fun registerUser(registrationRequest: RegistrationRequest){
        viewModelScope.launch {
            var response=userRepository.registerUser(registrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                registrationFailLiveData.postValue(response.errorBody()?.string())
            }
            }
        }
    }

