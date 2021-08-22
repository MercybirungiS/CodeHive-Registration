package com.example.codehiverregistration.ui

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.codehiverregistration.databinding.ActivityMainBinding

import com.example.codehiverregistration.models.RegistrationRequest
import com.example.codehiverregistration.viewmodel.UserViewModel
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels() //fatory pattern
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?)

    {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nationality = arrayListOf<String>("Kenyan", "Ugandan", "Rwandese", "South Sudanes")
        var nationalityAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationality)
        binding.spNationality.adapter = nationalityAdapter
        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.btnlogin1.setOnClickListener {
            var intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
            sharedPreferences = getSharedPreferences(Constant.SHAREDPREFS, Context.MODE_PRIVATE)

        }

        fun redirectUser() {
            var accessToken =
                sharedPreferences.getString(Constant.ACCESS_TOKEN, Constant.EMPTY_STRING)
            if (accessToken!!.isNotEmpty()) {
                startActivity(Intent(baseContext, CoursesResponseActivity::class.java))
            } else {
                startActivity(Intent(baseContext, LoginActivity::class.java))
            }
        }
    }

    //Read on android activity life cycle
    override fun onResume() {
        super.onResume()
        binding.btnRegister.setOnClickListener {
            if (binding.etName.text.toString().isEmpty() ||
                binding.etDOB.text.toString().isEmpty() ||
                binding.etPhoneNumber.text.toString().isEmpty() ||
                binding.etEmail.text.toString().isEmpty() ||
                binding.etPasswordRegister.text.toString().isEmpty()
            ) {
                binding.etName.setError("Name required")
                binding.etDOB.setError("Date of birth required")
                binding.etPhoneNumber.setError("Number required")
                binding.etEmail.setError("Email required")
                binding.etPasswordRegister.setError("Password required")

            }

            var name = binding.etName.text.toString()
            var Dob = binding.etDOB.text.toString()
            var phoneNumber = binding.etPhoneNumber.text.toString()
            var email = binding.etEmail.text.toString()
            var password = binding.etPasswordRegister.text.toString()

            var regRequest = RegistrationRequest(
                name = binding.etName.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                email = binding.etEmail.text.toString(),
                dateOfBirth = binding.etDOB.text.toString(),
                password = binding.etPasswordRegister.text.toString(),
                nationality = binding.spNationality.selectedItem.toString().uppercase()
            )

            var intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)

            userViewModel.registerUser(regRequest)
        }
        userViewModel.registrationLiveData.observe(this, { regResponse ->
            if (!regResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.registrationFailLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }
}






//    fun castViews() {
//        etName = findViewById(R.id.etName)
//        etDob = findViewById(R.id.etDOB)
//        spNationality = findViewById(R.id.spNationality)
//     etPassword = findViewById(R.id.etPasswordRegister)
//        etPhone = findViewById(R.id.etPhoneNumber)
//        etEmail = findViewById(R.id.etEmail)
//        btnRegister = findViewById(R.id.btnRegister)
//
//        var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
//        var nationalitiesAdapter =
//            ArrayAdapter(this, android.R.layout.simple_spinner_item, nationalities)
//        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spNationality.adapter = nationalitiesAdapter
//
//        clickRegister()
//    }
//
//    fun clickRegister() {
//        var error = false
//        btnRegister.setOnClickListener {
//            var name = etName.text.toString()
//            if (name.isEmpty()) {
//                error = true
//                etName.setError("Name is required")
//            }
//            var dob = etDob.text.toString()
//            var nationality = spNationality.selectedItem.toString()
//        var password1 = etPassword.text.toString()
//            var phone = etPhone.text.toString()
//            var email = etEmail.text.toString()
//            if (email.isEmpty()) {
//                error = true
//                etEmail.setError("email is required")
//            }
//
//            var registrationRequest = RegistrationRequest(
//                name = name,
//                phoneNumber = phone,
//                email = email,
//                nationality = nationality.uppercase(),
//                dateOfBirth = dob,
//                password = password1,
//            )}}
//            override fun onResume() {
//                super.onResume()
//                UserViewModel.registrationLiveData.observe(this, Observer {
//                        response->
//                    Toast.makeText(baseContext, "Registration Success", Toast.
//                    LENGTH_LONG).show()
//                })
//                UserViewModel.regError.observe(this, Observer { str->
//                    Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show(
//
////            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
////            var request = retrofit.registerStudent(registrationRequest)
////            request.enqueue(object : Callback<RegistrationResponse> {
////                override fun onResponse(
////                    call: Call<RegistrationResponse>,
////                    response: Response<RegistrationResponse>
////                ) {
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
////                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
////                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
////                }
////
////            })
//            var intent=Intent(baseContext, LoginActivity ::class.java)
//             startActivity(intent)
////        }
////    }
////}
//

