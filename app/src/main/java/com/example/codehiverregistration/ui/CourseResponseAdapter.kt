package com.example.codehiverregistration.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codehiverregistration.R
import com.example.codehiverregistration.models.CourseResponse

class CoursesResponseAdapter(var courseList: List<CourseResponse>) :RecyclerView.Adapter <CoursesResponseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesResponseViewHolder {
        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_response_list_item, parent, false)
        return CoursesResponseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesResponseViewHolder, position: Int) {
        var currentCourseResponse = courseList.get(position)

        holder.tvCourseId.text=currentCourseResponse.course_id
        holder.tvcoursename.text=currentCourseResponse.course_name
        holder.description.text=currentCourseResponse.description
        holder.tvInstructor.text=currentCourseResponse.instructor
        holder.tvCourseCode.text=currentCourseResponse.course_code

    }

    override fun getItemCount(): Int {
        return courseList.size

    }

}

class CoursesResponseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var tvCourseId=itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvcoursename=itemView.findViewById<TextView>(R.id.tvCourseNameR)
    var tvCourseCode=itemView.findViewById<TextView>(R.id.tvCourseCode)
    var description=itemView.findViewById<TextView>(R.id.tvDescriptionR)
    var tvInstructor=itemView.findViewById<TextView>(R.id.tvInstructorR)
}

