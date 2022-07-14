package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import kotlinx.android.synthetic.main.activity_registration.*

import org.json.JSONObject
import java.util.*

class EditProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        //BuildDate()

        update.setOnClickListener {

            // data we send in the request: Email and password
            val params = JSONObject()

            params.put("firstName", etFirstName.text.toString())
            params.put("lastName", etLastName.text.toString())
            params.put("email", etEmail.text.toString())
            params.put("phone", etPhone.text.toString())
            params.put("gender", etGender.text.toString())
            params.put("birth_day", etBOD.text.toString())
            params.put("national_id", etNationalID.text.toString())
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
                textView4.text = profile.getString("city")
                textView5.text = profile.getString("country")
                textView6.text = profile.getString("phone")
                textView8.text = profile.getString("national_id")
                textView7.text = profile.getString("gender")
                textView9.text = profile.getString("birth_day")

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
            R.id.seizureHistory -> {
                val intent = Intent(this@EditProfile, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord -> {
                val intent = Intent(this@EditProfile, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms -> {
                val intent = Intent(this@EditProfile, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory -> {
                val intent = Intent(this@EditProfile, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest -> {
                val intent = Intent(this@EditProfile, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm -> {
                val intent = Intent(this@EditProfile, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet -> {
                val intent = Intent(this@EditProfile, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure -> {
                val intent = Intent(this@EditProfile, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question -> {
                val intent = Intent(this@EditProfile, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout -> {
                val intent = Intent(this@EditProfile, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun BuildDate() {
        etBOD.addTextChangedListener(object : TextWatcher {
            private var current = ""
            private val ddmmyyyy = "DDMMYYYY"
            private val cal = Calendar.getInstance()
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]".toRegex(), "")
                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 6) {
                        sel++
                        i += 2
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean == cleanC) sel--
                    if (clean.length < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length)
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        var day = clean.substring(0, 2).toInt()
                        var mon = clean.substring(2, 4).toInt()
                        var year = clean.substring(4, 8).toInt()
                        if (mon > 12) mon = 12
                        cal[Calendar.MONTH] = mon - 1
                        year = if (year < 1900) 1900 else if (year > 2100) 2100 else year
                        cal[Calendar.YEAR] = year
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012
                        day = if (day > cal.getActualMaximum(Calendar.DATE)) cal.getActualMaximum(
                            Calendar.DATE
                        ) else day
                        clean = String.format("%02d%02d%02d", day, mon, year)
                    }
                    clean = String.format(
                        "%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8)
                    )
                    sel = if (sel < 0) 0 else sel
                    current = clean
                    etBOD.setText(current)
                    etBOD.setSelection(if (sel < current.length) sel else current.length)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })
    }
}