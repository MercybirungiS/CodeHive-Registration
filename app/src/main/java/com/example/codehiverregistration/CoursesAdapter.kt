package com.example.codehiverregistration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoursesAdapter(var courseList: List<Courses>) :RecyclerView.Adapter <CoursesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_layout_resource, parent, false)
        return CoursesViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourse = courseList.get(position)
       holder.tvCourseCode.text=currentCourse.courseCode
        holder.tvCourseName.text=currentCourse.courseName
        holder.tvDescription.text=currentCourse.description
        holder.tvInstructor.text=currentCourse.instructor

    }

    override fun getItemCount(): Int {
       return courseList.size
    }
}

class CoursesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var tvCourseName = itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvCourseCode = itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
}