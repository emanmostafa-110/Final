package com.example.afinal.Connection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.Adapter.ConnectionAdapter
import com.example.afinal.Information.Alarm
import com.example.afinal.Information.Diet
import com.example.afinal.Information.FrequentQuestion
import com.example.afinal.Information.SeizureInfo
import com.example.afinal.MedicalRecord
import com.example.afinal.R
import com.example.afinal.Signal.SeizureHistory
import com.example.afinal.Symptoms
import com.example.afinal.UI.Login
import com.example.afinal.UI.MyProfile
import com.example.finalseizures.MyRequestArray
import kotlinx.android.synthetic.main.activity_history_connection.*
import kotlinx.android.synthetic.main.activity_seizure_history.*
import org.json.JSONObject

class ConnectionRequest : AppCompatActivity() {

    private lateinit var connectionAdapter2: ConnectionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_request)

        initRecyclerView()
    }


    private fun initRecyclerView(): ArrayList<String> {

        rv_list_request.apply {
            layoutManager = LinearLayoutManager(this@ConnectionRequest)

            connectionAdapter2 = ConnectionAdapter()
            adapter = connectionAdapter2


            var list = arrayListOf<String>()

            Log.d("mytag", "Button clicked")

            // send request
            val queue = Volley.newRequestQueue(this@ConnectionRequest)
            val request = MyRequestArray(
                this@ConnectionRequest,
                Request.Method.GET,
                "/connectionRequest",
                null,
                { response ->

                    Log.d("mytag", "$response")

                    for (i in 0 until  response.length()){

                        val test: JSONObject = response.getJSONObject(i)

                        // get the current student (json object) data
                        list.add(test.getString("name"))
                        list.add(test.getString("address"))
                        list.add(test.getString("phone"))
                        list.add(test.getString("email"))

                    }
                    Log.d("mytag", "$list")

                },
                { error ->
                    Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                    Toast.makeText(this@ConnectionRequest, "Connection error", Toast.LENGTH_SHORT).show()
                }
            )
            queue.add(request)

            return list
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
                val intent = Intent(this@ConnectionRequest, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory ->{
                val intent = Intent(this@ConnectionRequest, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@ConnectionRequest, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@ConnectionRequest, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@ConnectionRequest, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@ConnectionRequest, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@ConnectionRequest, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@ConnectionRequest, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@ConnectionRequest, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@ConnectionRequest, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@ConnectionRequest, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}