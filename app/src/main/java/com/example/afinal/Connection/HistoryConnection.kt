package com.example.afinal.Connection

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
import com.example.afinal.Adapter.HistoryAdapter
import com.example.afinal.Alarm.Alarm
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.MedicalRecord
import com.example.afinal.Models.DoctorData
import com.example.afinal.R
import com.example.afinal.Signal.SeizureHistory
import com.example.afinal.Symptoms
import com.example.afinal.UI.Login
import com.example.afinal.UI.MyProfile
import com.example.finalseizures.MyRequestArray
import kotlinx.android.synthetic.main.activity_connection_request.*
import kotlinx.android.synthetic.main.activity_history_connection.*
import kotlinx.android.synthetic.main.activity_seizure_history.*

class HistoryConnection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_connection)
        initRecyclerView2()

    }

    private fun initRecyclerView2() {

        var list = ArrayList<DoctorData>()

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this@HistoryConnection)

        val request = MyRequestArray(
            this@HistoryConnection,
            Request.Method.GET,
            "/connectionData",
            null,
            { response ->

                Log.d("mytag", "$response")

                if(response.length() > 0) {

                    for (i in 0 until response.length()) {

                        textConnection2.visibility = View.GONE

                        val test = response.getJSONObject(i)

                        // get the current student (json object) data
                        list.add(
                            DoctorData(
                                "Name: ${test.getString("name")}",
                                "Address: ${test.getString("address")}",
                                "phone: ${test.getString("phone")}"
                            )
                        )

                        rv_list_history.layoutManager = LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL, false
                        )

                        var HistoryAdapter = HistoryAdapter(list)

                        rv_list_history.adapter = HistoryAdapter

                        HistoryAdapter.setonItemClickListener(object: HistoryAdapter.onItemClickListener{

                            override fun delete_action(position: Int) {

                            Toast.makeText(this@HistoryConnection
                                ,"OKKKK",
                                Toast.LENGTH_LONG).show()
                            }
                        })
                    }
                }
                Log.d("mytag", "$list")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this@HistoryConnection, "Connection error", Toast.LENGTH_SHORT)
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
                val intent = Intent(this@HistoryConnection, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory ->{
                val intent = Intent(this@HistoryConnection, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@HistoryConnection, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@HistoryConnection, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@HistoryConnection, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@HistoryConnection, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@HistoryConnection, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@HistoryConnection, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@HistoryConnection, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@HistoryConnection, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@HistoryConnection, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}