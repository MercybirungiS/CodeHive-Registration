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
   val loginViewModel:LoginViewModel by viewModels()
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sharedPreferences =getSharedPreferences("CODEHIVE_REG_PTRFS",Context.MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            var loginRequest=LoginRequest(binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString())
            loginViewModel.logIn(loginRequest)
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.loginLiveData.observe(this, { logInResponse->
            Toast.makeText(baseContext, logInResponse.message, Toast.LENGTH_LONG).show()

            var accessToken = logInResponse.token
            sharedPreferences.edit().putString("ACCESS_TOKEN", accessToken).apply()

            var x = sharedPreferences.getString("ACCESS_TOKEN", "")
        })

        loginViewModel.loginFailLiveData.observe(this, {error->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
            binding.tvLoginError.visibility=View.VISIBLE
            binding.tvLoginError.text = error
        })
    }
}


//            var loginRequest = LoginRequest(
//
//                email = email,
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
