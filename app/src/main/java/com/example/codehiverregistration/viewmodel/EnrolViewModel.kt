package com.example.codehiverregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehiverregistration.models.EnrollmentResponse
import com.example.codehiverregistration.repository.CoursesRepository
import kotlinx.coroutines.launch

class EnrolViewModel:ViewModel() {
    var enrolLiveData=MutableLiveData<EnrollmentResponse>()
    var enroFailLiveData=MutableLiveData<String>()
    var courseRepository=CoursesRepository()

    fun getEnrolment(accessToken:String){
        viewModelScope.launch {
            var response=courseRepository.enrollment(accessToken)
            if (response.isSuccessful){
                enrolLiveData.postValue(response.body())
            }
            else{
                enroFailLiveData.postValue(response.errorBody()?.toString())
            }
        }
    }
}