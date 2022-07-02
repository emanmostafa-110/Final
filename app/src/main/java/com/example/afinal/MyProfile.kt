package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.finalseizures.MyRequest

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)


        val firstName= findViewById<TextView>(R.id.firstName_edit)
        val lastName= findViewById<TextView>(R.id.lastName_edit)
        val email= findViewById<TextView>(R.id.email_edit)
        val phone= findViewById<TextView>(R.id.phone_edit)
        val country= findViewById<TextView>(R.id.country_edit)
        val city= findViewById<TextView>(R.id.city_edit)
        val gender= findViewById<TextView>(R.id.gender_edit)
        val birthDate= findViewById<TextView>(R.id.birthDate_edit)
        val nationalId= findViewById<TextView>(R.id.nationalId_edit)



        val edit= findViewById<Button>(R.id.Btn_edit)
        val back= findViewById<Button>(R.id.Btn_back)


        edit.setOnClickListener{
            val intent = Intent(this@MyProfile, EditProfile::class.java)
            startActivity(intent)
        }

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.GET,
            "/profile",
            null,
            { response ->

                Log.d("mytag", "response = $response")
                val profile = response.getJSONObject("Patient")

                firstName.text = profile.getString("firstName").toString()
                lastName.text = profile.getLong("lastName").toString()
                email.text = profile.getString("email")
                city.text = profile.getString("city")
                country.text = profile.getString("country")
                phone.text = profile.getString("phone")
                gender.text = profile.getString("gender")
                nationalId.text = profile.getString("national_id")
                birthDate.text = profile.getString("date")

                // if there is an error (wrong email or password)
                if (response.has("error")) {
                    val errorMesssage = response.getString("error")
                    Toast.makeText(this, errorMesssage, Toast.LENGTH_SHORT).show()

                }
            },
            { error ->
                Log.e(
                    "mytag",
                    "Error: $error - Status Code = ${error.networkResponse?.statusCode}"
                )
                Toast.makeText(this, "Connection error", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(request)

    }
}