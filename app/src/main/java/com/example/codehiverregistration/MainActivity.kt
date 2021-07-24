package com.example.codehiverregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.codehiverregistration.api.ApiClient
import com.example.codehiverregistration.api.ApiInterface

import com.example.codehiverregistration.modules.RegistrationRequest
import com.example.codehiverregistration.modules.RegistrationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etDob: EditText
    lateinit var spNationality: Spinner
    lateinit var etPassword: EditText
    lateinit var etPhone: EditText
    lateinit var etEmail: EditText
    lateinit var btnRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         castViews()

    }

    fun castViews() {
        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDOB)
        spNationality = findViewById(R.id.spNationality)
     etPassword = findViewById(R.id.etPasswordRegister)
        etPhone = findViewById(R.id.etPhoneNumber)
        etEmail = findViewById(R.id.etEmail)
        btnRegister = findViewById(R.id.btnRegister)

        var nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Sudanese", "Ugandan")
        var nationalitiesAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter = nationalitiesAdapter

        clickRegister()
    }

    fun clickRegister() {
        var error = false
        btnRegister.setOnClickListener {
            var name = etName.text.toString()
            if (name.isEmpty()) {
                error = true
                etName.setError("Name is required")
            }
            var dob = etDob.text.toString()
            var nationality = spNationality.selectedItem.toString()
        var password1 = etPassword.text.toString()
            var phone = etPhone.text.toString()
            var email = etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                etEmail.setError("email is required")
            }

            var registrationRequest = RegistrationRequest(
                name = name,
                phoneNumber = phone,
                email = email,
                nationality = nationality.uppercase(),
                dateOfBirth = dob,
                password = password1,
            )

            val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
            var request = retrofit.registerStudent(registrationRequest)
            request.enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
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

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }

            })
            var intent=Intent(baseContext,LoginActivity ::class.java)
             startActivity(intent)
        }
    }
}


