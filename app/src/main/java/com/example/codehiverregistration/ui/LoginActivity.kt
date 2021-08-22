package com.example.codehiverregistration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.codehiverregistration.R
import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface
import com.example.codehiverregistration.databinding.ActivityLoginBinding
import com.example.codehiverregistration.models.LoginRequest
import com.example.codehiverregistration.models.LoginResponse
import com.example.codehiverregistration.viewmodel.LoginViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    val loginViewModel: LoginViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(Constant.SHAREDPREFS, Context.MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            var loginRequest = LoginRequest(
                binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString()
            )
            loginViewModel.logIn(loginRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            binding.tvLoginError.visibility = View.GONE
            validateLogIn()

        }
        loginViewModel.loginLiveData.observe(this, { logInResponse ->
            binding.progressBar.visibility = View.GONE
            Toast.makeText(baseContext, logInResponse.message, Toast.LENGTH_LONG).show()
//            var accessToken = logInResponse.accessToken
            var editor = sharedPreferences.edit()
            sharedPreferences.edit().putString(Constant.ACCESS_TOKEN, logInResponse.token).apply()
            editor.putString(Constant.ACCESS_TOKEN, logInResponse.token)
            editor.putString(Constant.STUDENTID, logInResponse.student_id)
            editor.apply()
            //create a session manager
            //figure out how to add a log out - remove the session manager
//            var x = sharedPreferences.getString("ACCESS_TOKEN", "")
            sharedPreferences.edit().putString(Constant.ACCESS_TOKEN,logInResponse.token).apply()
            startActivity(Intent(baseContext, CoursesResponseActivity::class.java))
        })

        loginViewModel.loginFailLiveData.observe(this, { error ->
            binding.progressBar.visibility=View.GONE
            binding.tvLoginError.visibility = View.VISIBLE
            binding.tvLoginError.text = error
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

        })
    }


    fun validateLogIn() {
        var email = binding.etEmailLogin.text.toString()
        var password = binding.etPasswordLogin.text.toString()
        var error = false

        if (email.isBlank() || email.isEmpty()) {
            var error = true
            binding.etEmailLogin.setError("Email is required")
        }
        if (password.isBlank() || password.isEmpty()) {
            var error = true
            binding.etEmailLogin.setError("Password is required")
        }
        if (!error) {
            binding.progressBar.visibility = View.GONE
        }
        var loginRequest=LoginRequest(
            email=email,
            password=password
        )
    }
}
//               il,
//                password = password
//            )}}}
//
////            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
////            var request = retrofit.loginStudent(loginRequest)
////            request.enqueue(object : Callback<LoginResponse> {
////                override fun onResponse(
////                    call: Call<LoginResponse>, response: Response<LoginResponse>) {
////                    if (response.isSuccessful) {
////                        Toast.makeText(baseContext, "Registration Successful", Toast.LENGTH_LONG)
////                            .show()
////                    } else {
////                        try {
////                            val error = JSONObject(response.errorBody()!!.string())
////                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
////
////
////                        } catch (e: Exception) {
////                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
////                        }
////                    }
////
////                }
////
////                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
////                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
////                }
////
////            })
////            var intent=Intent(baseContext, CoursesActivity ::class.java)
////            startActivity(intent)
////        }
////    }
////}
//
