package com.example.codehiverregistration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codehiverregistration.models.CourseResponse
import com.example.codehiverregistration.repository.CoursesRepository
import kotlinx.coroutines.launch

class CoursesViewModel :ViewModel() {
    var courseLiveData= MutableLiveData<List<CourseResponse>>()
    var courseFailLiveData= MutableLiveData<String>()
    var courseRepository= CoursesRepository()

    fun getCourses(token: String) {
        viewModelScope.launch {
            var response=courseRepository.courses(token)
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                courseFailLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

