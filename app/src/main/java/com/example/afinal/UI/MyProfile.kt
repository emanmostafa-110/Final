package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.Alarm.Alarm
import com.example.afinal.Connection.ConnectionRequest
import com.example.afinal.Connection.HistoryConnection
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.MedicalRecord
import com.example.afinal.R
import com.example.afinal.Signal.SeizureHistory
import com.example.afinal.Symptoms
import com.example.finalseizures.MyRequest
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_registration.*

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        supportActionBar?.hide()

        edit.setOnClickListener {
            val intent = Intent(this@MyProfile, EditProfile::class.java)
            startActivity(intent)
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

                yourName.text = "${profile.getString("firstName")} ${profile.getString("lastName")}"
                yourEmail.text = profile.getString("email")
                yourPhone.text = profile.getLong("phone").toString()
                yourAddress.text ="${profile.getString("country")} / ${profile.getString("city")}"
                DateBirth.text = profile.getString("birth_day")
                yourGender.text = profile.getString("gender")
                Yournational_id.text=profile.getString("national_id")

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
                val intent = Intent(this@MyProfile, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory -> {
                val intent = Intent(this@MyProfile, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord -> {
                val intent = Intent(this@MyProfile, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms -> {
                val intent = Intent(this@MyProfile, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory -> {
                val intent = Intent(this@MyProfile, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest -> {
                val intent = Intent(this@MyProfile, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm -> {
                val intent = Intent(this@MyProfile, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet -> {
                val intent = Intent(this@MyProfile, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure -> {
                val intent = Intent(this@MyProfile, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question -> {
                val intent = Intent(this@MyProfile, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                val intent = Intent(this@MyProfile, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}