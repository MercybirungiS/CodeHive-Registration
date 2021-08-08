package com.example.codehiverregistration.ui

import android.R
import android.content.Intent
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
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nationalities = arrayOf("KENYAN",  "UGANDAN")
        val nationalitiesAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalitiesAdapter




    }

    override fun onResume() {
        super.onResume()
        binding.btnRegister.setOnClickListener {
            var registrationRequest=RegistrationRequest(
                name = binding.etName.toString(),
                phoneNumber = binding.etEmail.toString(),
                email = binding.etEmail.toString(),
                dateOfBirth = binding.etDOB.text.toString(),
                password = binding.etPasswordRegister.text.toString(),
                nationality = binding.spNationality.toString()



            )

            userViewModel.registerUser(registrationRequest)
        }
        userViewModel.registrationLiveData.observe(this,
            {registrationResponse->
                if (!registrationResponse.studentId.isNullOrEmpty()){
                    var intent= Intent(baseContext,LoginActivity ::class.java)
                    startActivity(intent)
                    Toast.makeText(baseContext,"Registration is not  successful",Toast.LENGTH_LONG).show()
                }


        })
        userViewModel.registrationFailLiveData.observe(this,{
            error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
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

