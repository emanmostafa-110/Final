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
import com.example.finalseizures.MyRequest
import com.example.finalseizures.MyRequestArray
import kotlinx.android.synthetic.main.activity_connection_request.*
import org.json.JSONObject
import kotlin.collections.ArrayList

class ConnectionRequest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_request)

        initRecyclerView()

    }

    private fun initRecyclerView() {

        var list = ArrayList<DoctorData>()

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

                if(response.length() > 0) {

                    for (i in 0 until response.length()) {

                        textConnection.visibility = View.GONE

                        val test = response.getJSONObject(i)

                        // get the current student (json object) data
                        list.add(
                            DoctorData(
                                "Name: ${test.getString("name")}",
                                test.getString("address"),
                                test.getString("phone")
                            )
                        )

                        rv_list_request.layoutManager = LinearLayoutManager(
                            this,
                            RecyclerView.VERTICAL, false
                        )

                        var ConnectionAdapter = ConnectionAdapter(list)

                        rv_list_request.adapter = ConnectionAdapter

                        ConnectionAdapter.setonItemClickListener(object: ConnectionAdapter.onItemClickListener{

                            override fun accept_action(position: Int) {

                                sendID(response.getJSONObject(position).getInt("id"))

                                val intent =Intent(this@ConnectionRequest,
                                ConnectionRequest::class.java)
                                startActivity(intent)

                            }
                            override fun reject_action(position: Int) {

                                sendID2(response.getJSONObject(position).getInt("id"))
                                val intent =Intent(this@ConnectionRequest,
                                    ConnectionRequest::class.java)
                                startActivity(intent)
                            }
                        })
                    }
                }
                Log.d("mytag", "$list")

            },
            { error ->
                Log.e("mytag", "Error: $error - Status Code = ${error.networkResponse?.statusCode}")
                Toast.makeText(this@ConnectionRequest, "Connection error", Toast.LENGTH_SHORT)
                    .show()
            }
        )

        queue.add(request)


    }

    private fun sendID(ID: Int){

        val params = JSONObject()

        params.put("connectionId",ID)

        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.POST,
            "/AcceptRequest",
            params,
            { response ->

                Log.d("mytag", "response = $response")

                // goto Login activity
                Toast.makeText(this@ConnectionRequest,
                    "${response.getString("message")}",
                Toast.LENGTH_LONG).show()

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

    private fun sendID2(ID: Int){

        val params = JSONObject()

        params.put("connectionId",ID)

        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.POST,
            "/RejectRequest",
            params,
            { response ->

                Log.d("mytag", "response = $response")

                // goto Login activity
                Toast.makeText(this@ConnectionRequest,
                    "${response.getString("message")}",
                    Toast.LENGTH_LONG).show()

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

