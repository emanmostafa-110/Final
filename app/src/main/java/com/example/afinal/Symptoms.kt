package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.Connection.ConnectionRequest
import com.example.afinal.Connection.HistoryConnection
import com.example.afinal.Alarm.Alarm
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.Signal.SeizureHistory
import com.example.afinal.UI.Login
import com.example.afinal.UI.MainActivity
import com.example.afinal.UI.MyProfile
import com.example.finalseizures.MyRequest
import kotlinx.android.synthetic.main.activity_symptoms.*
import org.json.JSONObject

class Symptoms : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        btn_back.setOnClickListener {
            val intent = Intent(this@Symptoms, MainActivity::class.java)
            startActivity(intent)
        }

        btn_save.setOnClickListener {

            val params = JSONObject()

            params.put("et_1",  et_1.text.toString())
            params.put("et_2", et_2.text.toString())
            params.put("et_3",   et_3.text.toString())
            params.put("et_4",  et_4.text.toString())
            params.put("et_5", et_5.text.toString())
            params.put("et_6",  et_6.text.toString())



            Log.d("mytag", "Button clicked")

            // send request
            val queue = Volley.newRequestQueue(this)
            val request = MyRequest(
                this,
                Request.Method.POST,
                "/symptomStore",
                params,
                { response ->

                    Log.d("mytag", "response = $response")


                    Toast.makeText(this, "Save Success", Toast.LENGTH_SHORT).show()
                    // goto MedicalRecord activity
                    val intent = Intent(this@Symptoms, MedicalRecord::class.java)
                    startActivity(intent)
                    finish()


                    if (response.has("message")) {
                        val errorMesssage = response.getString("message")
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



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menue, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this@Symptoms, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory->{
                val intent = Intent(this@Symptoms, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@Symptoms, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@Symptoms, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@Symptoms, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@Symptoms, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@Symptoms, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@Symptoms, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@Symptoms, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@Symptoms, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@Symptoms, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}