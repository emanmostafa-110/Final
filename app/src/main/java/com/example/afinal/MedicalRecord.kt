package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.Adapter.ConnectionAdapter
import com.example.afinal.Adapter.SymptomsAdapter
import com.example.afinal.Connection.ConnectionRequest
import com.example.afinal.Connection.HistoryConnection
import com.example.afinal.Alarm.Alarm
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.Models.DoctorData
import com.example.afinal.Models.SymptomsData
import com.example.afinal.Signal.SeizureHistory
import com.example.afinal.UI.Login
import com.example.afinal.UI.MainActivity
import com.example.afinal.UI.MyProfile
import com.example.finalseizures.MyRequestArray
import kotlinx.android.synthetic.main.activity_connection_request.*
import kotlinx.android.synthetic.main.activity_connection_request.rv_list_request
import kotlinx.android.synthetic.main.activity_medical_record.*
import kotlinx.android.synthetic.main.activity_seizure_history.*
import org.json.JSONObject
import java.util.ArrayList

class MedicalRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record)


        good.setOnClickListener {
            val intent = Intent(this@MedicalRecord, MainActivity::class.java)
            startActivity(intent)

        }
        getDataConnection()
    }

    private fun getDataConnection(){
        var list = ArrayList<SymptomsData>()

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this@MedicalRecord)

        val request = MyRequestArray(
            this@MedicalRecord,
            Request.Method.GET,
            "/symptomData",
            null,
            { response ->

                Log.d("mytag", "$response")

                if(response.length() > 0) {

                    for (i in 0 until response.length()) {

                       textConnection3.visibility = View.GONE

                        val test = response.getJSONObject(i)

                        // get the current student (json object) data
                        list.add(
                            SymptomsData(
                                "Temporary confusion: ${test.getString("et_1")}",
                                "Staring spell: ${test.getString("et_2")}",
                                "Stiff muscles: ${test.getString("et_3")}",
                                "Uncontrollable movements: ${test.getString("et_4")}",
                                "Loss of consciousness: ${test.getString("et_5")}",
                                "Psychological symptoms: ${test.getString("et_6")}",
                                "Created At: ${test.getString("created_at")}"

                            )
                        )

                        rv_symptom_history.layoutManager = LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL, false
                        )

                        var SymptomsAdapter = SymptomsAdapter(list)

                        rv_symptom_history.adapter = SymptomsAdapter


                    }
                }
                Log.d("mytag", "$list")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this@MedicalRecord, "Connection error", Toast.LENGTH_SHORT)
                    .show()
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
                val intent = Intent(this@MedicalRecord, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory->{
                val intent = Intent(this@MedicalRecord, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@MedicalRecord, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@MedicalRecord, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@MedicalRecord, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@MedicalRecord, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@MedicalRecord, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@MedicalRecord, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@MedicalRecord, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@MedicalRecord, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@MedicalRecord, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}