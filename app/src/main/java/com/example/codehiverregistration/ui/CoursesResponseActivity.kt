package com.example.codehiverregistration.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codehiverregistration.R
import com.example.codehiverregistration.databinding.ActivityCoursesBinding
import com.example.codehiverregistration.models.CourseResponse
import com.example.codehiverregistration.viewmodel.CoursesViewModel

class CoursesResponseActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoursesBinding
    val courseViewModel:CoursesViewModel by viewModels()

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
      sharedPreferences =getSharedPreferences("CODEHIVE_REG_PTRFS", Context.MODE_PRIVATE)

    }

    override fun onResume() {
        super.onResume()
        var
    }

}

//    override fun onResume() {
//        super.onResume()
//
//        var rvCourses=binding.rvCourses
//        courseViewModel.courseLiveData.observe(this,{
//            courseViewModel.courseResponse(courseResponse = CourseResponse())
//            var coursesResponseAdapter=CoursesResponseAdapter(CourseResponse)
//            rvCourses.layoutManager=LinearLayoutManager(baseContext)
//            rvCourses.adapter=coursesResponseAdapter
//
//        })
//    }
//}