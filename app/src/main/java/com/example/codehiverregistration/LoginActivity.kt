package com.example.codehiverregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface
import com.example.codehiverregistration.modules.LoginRequest
import com.example.codehiverregistration.modules.LoginResponse
import com.example.codehiverregistration.modules.RegistrationRequest
import com.example.codehiverregistration.modules.RegistrationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    lateinit var etEmail:EditText
    lateinit var etPassword:EditText
    lateinit var btnlogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        castviews()
    }
    fun castviews(){
        etEmail=findViewById(R.id.etEmailLogin)
        etPassword=findViewById(R.id.etPasswordLogin)
        btnlogin=findViewById(R.id.btnLogin)
        clickRegister()
    }
    fun clickRegister() {
        var error = false
        btnlogin.setOnClickListener {
            var name = etEmail.text.toString()
            if (name.isEmpty()) {
                error = true
                etEmail.setError("Name is required")
            }

            var password = etPassword.text.toString()

            var email = etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                etEmail.setError("email is required")
            }

            var loginRequest = LoginRequest(

                email = email,
                password = password
            )

            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = retrofit.loginStudent(loginRequest)
            request.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        Toast.makeText(baseContext, "Registration Successful", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()


                        } catch (e: Exception) {
                            Toast.makeText(baseContext, error.toString(), Toast.LENGTH_LONG).show()
                        }
                    }

                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
            var intent=Intent(baseContext,CoursesActivity ::class.java)
            startActivity(intent)
        }
    }
}

