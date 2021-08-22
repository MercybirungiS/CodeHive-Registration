package com.example.codehiverregistration.models

import com.google.gson.annotations.SerializedName

data class CourseResponse(


    @SerializedName("course_name")  var course_name:String,
    @SerializedName("course_code") var course_code:String,
    var description:String,
    var instructor:String,


    )
