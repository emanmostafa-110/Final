package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.finalseizures.MyRequest
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_registration.*

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)





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
                val profile = response.getJSONObject("data")

                firstName.text = profile.getString("firstName").toString()
                lastName.text = profile.getLong("lastName").toString()
                yourEmail.text = profile.getString("email")
                city.text = profile.getString("city")
                country.text = profile.getString("country")
                yourPhone.text = profile.getString("phone")
                gender.text = profile.getString("gender")
                nationalID.text = profile.getString("national_id")
                DateBirth.text = profile.getString("date")

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