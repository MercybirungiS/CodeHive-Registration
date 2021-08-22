package com.example.codehiverregistration.models

import com.google.gson.annotations.SerializedName

data class EnrollmentRequest(
    @SerializedName("course_id")var courseId:String,
    @SerializedName("student_id")var studentId:String,
)
