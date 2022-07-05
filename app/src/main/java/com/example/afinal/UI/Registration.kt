package com.example.afinal.UI

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.R
import com.example.finalseizures.MyRequest
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.et_email
import org.json.JSONObject

class Registration : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.S)

    val gender = arrayOf("Female","Male")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

       // val arrayAdapter =ArrayAdapter<String>(this,)


        btn_registration.setOnClickListener {

            if (validate()) {

                // data we send in the request: Email and password
                val params = JSONObject()

                params.put("firstName",  et_fname.text.toString())
                params.put("lastName", et_lname.text.toString())
                params.put("email",   et_email.text.toString())
                params.put("password",  et_pass.text.toString())
                params.put("phone", et_phone.text.toString())
                params.put("gender",  et_gender.text.toString())
                params.put("birth_day", et_birthdate.text.toString())
                params.put("national_id",et_nationalID.text.toString())
                params.put("city", et_city.text.toString())
                params.put("country", et_country.text.toString())


                Log.d("mytag", "Button clicked")

                // send request
                val queue = Volley.newRequestQueue(this)
                val request = MyRequest(
                    this,
                    Request.Method.POST,
                    "/register",
                    params,
                    { response ->

                        Log.d("mytag", "response = $response")

                        // goto Login activity
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                        finish()

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
    }
    fun validate(): Boolean {
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_password = findViewById<EditText>(R.id.et_pass)
        val date = findViewById<EditText>(R.id.et_birthdate)
        val et_city = findViewById<EditText>(R.id.et_city)
        val et_country = findViewById<EditText>(R.id.et_country)
        //val et_gender = findViewById<EditText>(R.id.et_gender)
        val et_nationalID = findViewById<EditText>(R.id.et_nationalID)
        val et_firstName = findViewById<EditText>(R.id.et_fname)
        val et_lastName = findViewById<EditText>(R.id.et_lname)
        val et_phone = findViewById<EditText>(R.id.et_phone)


        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        if (et_firstName.text.toString().isNotEmpty() && et_lastName.text.toString()
                .isNotEmpty()
            && (et_nationalID.text.toString()
                .isNotEmpty() && et_nationalID.text.toString().length == 14)
            && (et_email.text.toString().trim().matches(emailPattern.toRegex()) &&
                    et_email.text.toString().trim().isNotEmpty()) &&
            et_password.text.toString().isNotEmpty() &&
            et_city.text.toString().isNotEmpty() &&
            et_country.text.toString().isNotEmpty() &&
            et_phone.text.toString().isNotEmpty() &&
            et_gender.text.toString().isNotEmpty()
        ) {

            return true
        } else {

            if (!et_email.text.toString().trim().matches(emailPattern.toRegex()))
                Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_LONG).show()
            else if (et_email.text.toString().trim().isEmpty())
                Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show()

            if (et_firstName.text.toString().isEmpty())
                Toast.makeText(this, "Enter your First Name", Toast.LENGTH_LONG).show()

            if (et_lastName.text.toString().isEmpty())
                Toast.makeText(this, "Enter your Last Name", Toast.LENGTH_LONG).show()

            if (et_nationalID.text.toString().isEmpty())
                Toast.makeText(this, "Enter your national ID", Toast.LENGTH_LONG).show()
            /*else if (et_nationalID.text.toString().length != 14)
                Toast.makeText(
                    this,
                    "Enter your Valid national ID (14 Number)",
                    Toast.LENGTH_LONG
                ).show()*/


            if (et_city.text.toString().isEmpty())
                Toast.makeText(this, "Enter your City", Toast.LENGTH_LONG).show()

            if (et_country.text.toString().isEmpty())
                Toast.makeText(this, "Enter your Country", Toast.LENGTH_LONG).show()

            if (et_phone.text.toString().isEmpty())
                Toast.makeText(this, "Enter your phone", Toast.LENGTH_LONG).show()
            if (et_gender.text.toString().isEmpty())
                Toast.makeText(this, "Enter your gender", Toast.LENGTH_LONG).show()

            if (et_password.text.toString().isEmpty())
                Toast.makeText(this, "Enter your password", Toast.LENGTH_LONG).show()

            return false

        }
    }
}
