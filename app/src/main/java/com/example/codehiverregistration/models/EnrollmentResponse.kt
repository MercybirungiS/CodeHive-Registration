package com.example.codehiverregistration.models

import com.google.gson.annotations.SerializedName

data class EnrollmentResponse(

    @SerializedName("course_id") var courseId:String,
    @SerializedName("enrolment_id")var enrolmentId:String,
)
