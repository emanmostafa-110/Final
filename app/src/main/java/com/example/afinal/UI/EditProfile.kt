package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.*
import com.example.afinal.Alarm.Alarm
import com.example.afinal.Connection.ConnectionRequest
import com.example.afinal.Connection.HistoryConnection
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.Signal.SeizureHistory
import com.example.finalseizures.MyRequest
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.json.JSONObject

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        update.setOnClickListener {



                // data we send in the request: Email and password
                val params = JSONObject()

                params.put("firstName",  etFirstName.text.toString())
                params.put("lastName", etLastName.text.toString())
                params.put("email",   etEmail.text.toString())
                params.put("phone", etPhone.text.toString())
                params.put("gender",  etGender.text.toString())
                params.put("birth_day", etBOD.text.toString())
                params.put("national_id",etNationalID.text.toString())
                params.put("city", etCity.text.toString())
                params.put("country", etCountry.text.toString())


                Log.d("mytag", "Button clicked")

                // send request
                val queue = Volley.newRequestQueue(this)
                val request = MyRequest(
                    this,
                    Request.Method.POST,
                    "/profileUpdate",
                    params,
                    { response ->

                        Log.d("mytag", "response = $response")

                        // goto Login activity
                        val intent = Intent(this@EditProfile, MyProfile::class.java)
                        startActivity(intent)


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

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.GET,
            "/profileShow",
            null,
            { response ->

                Log.d("mytag", "response = $response")

                val profile = response.getJSONObject("data")

                textView1.text = profile.getString("firstName")
                textView2.text = profile.getString("lastName")
                textView3.text = profile.getString("email")
                textView4.text =profile.getString("city")
                textView5.text = profile.getString("country")
                textView6.text = profile.getString("phone")
                textView7.text=profile.getString("national_id")
                textView8.text = profile.getString("gender")
                textView9.text=profile.getString("birth_day")

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
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menue, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this@EditProfile, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory->{
                val intent = Intent(this@EditProfile, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@EditProfile, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@EditProfile, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@EditProfile, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@EditProfile, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@EditProfile, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@EditProfile, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@EditProfile, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@EditProfile, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@EditProfile, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}