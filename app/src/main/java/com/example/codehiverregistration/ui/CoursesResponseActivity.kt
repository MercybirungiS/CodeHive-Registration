package com.example.codehiverregistration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codehiverregistration.databinding.ActivityCoursesResponseBinding
import com.example.codehiverregistration.viewmodel.CoursesViewModel

class CoursesResponseActivity : AppCompatActivity() {
    lateinit var binding:ActivityCoursesResponseBinding
    val courseViewModel:CoursesViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCoursesResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences=getSharedPreferences(Constant.SHAREDPREFS,Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        var accessToken=sharedPreferences.getString(Constant.toString(),"TOKEN")
        var bearer="Bearer $accessToken"
        if (accessToken!!.isNotEmpty()){
            courseViewModel.getCourses(bearer)
        }
        else{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }
        var rvcourseResponse=binding.rvCourseResponse
        rvcourseResponse.layoutManager=LinearLayoutManager(baseContext)
        courseViewModel.courseLiveData.observe(this,{coursesResponse->
            var coursesResponseAdapter=CoursesResponseAdapter(coursesResponse)
            rvcourseResponse.adapter=coursesResponseAdapter
        })
        courseViewModel.courseFailLiveData.observe(this,{ error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
        courseViewModel.getCourses(accessToken)
    }
}
//      sharedPreferences =getSharedPreferences("CODEHIVE_REG_PTRFS", Context.MODE_PRIVATE)


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