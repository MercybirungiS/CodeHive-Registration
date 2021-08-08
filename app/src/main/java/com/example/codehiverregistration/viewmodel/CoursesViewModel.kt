package com.example.codehiverregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehiverregistration.models.CourseResponse
import com.example.codehiverregistration.models.LoginRequest
import com.example.codehiverregistration.models.LoginResponse
import com.example.codehiverregistration.repository.CoursesRepository
import com.example.codehiverregistration.repository.UserRepository
import kotlinx.coroutines.launch

class CoursesViewModel :ViewModel() {
    var courseLiveData= MutableLiveData<CourseResponse>()
    var courseFailLiveData= MutableLiveData<String>()
    var courseRepository= CoursesRepository()

    fun courseResponse(courseResponse: CourseResponse){
        viewModelScope.launch {
            var response=courseRepository.courseResponse(courseResponse)
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                courseFailLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

