package com.example.codehiverregistration.models

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context:Context){
    var sharedPreferences:SharedPreferences=context.getSharedPreferences("CODE_HIVE_PREFS",Context.MODE_PRIVATE)
    fun saveAccToken(token:String){
        sharedPreferences.edit().putString("ACCESS_TOKEN",token).apply()

    }
    fun fetchAccToken():String?{
        return sharedPreferences.getString("ACCESS_TOKEN","")
    }
}