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
import com.example.finalseizures.MyRequest
import com.example.finalseizures.MyRequestArray
import kotlinx.android.synthetic.main.activity_history_connection.*
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.list_history_of_connection.view.*
import org.json.JSONObject

class HistoryConnection : AppCompatActivity() {

    private lateinit var connectionAdapter: ConnectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_connection)



        // this creates a vertical layout Manager
        //rv_list_history.layoutManager = LinearLayoutManager(this)

       /* // ArrayList of class ItemsViewModel
        val data = ArrayList<ServicesData>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ServicesData(DoctorName.toString()))

        }

        // This will pass the ArrayList to our Adapter
        val adapter = ConnectionAdapter(data)

        // Setting the Adapter with the recyclerview
        rv_list_history.adapter = adapter*/

        initRecyclerView()
        //addDataSet()


    }
/*
    private fun addDataSet(){
        val data = DataSource.createDataSet()
        ConnectionAdapter.submitList(data)
    }*/

    private fun initRecyclerView(): ArrayList<String> {

        rv_list_history.apply {
            layoutManager = LinearLayoutManager(this@HistoryConnection)

            connectionAdapter = ConnectionAdapter()
            adapter = connectionAdapter


            var list = arrayListOf<String>()

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
                    Toast.makeText(this@HistoryConnection, "Connection error", Toast.LENGTH_SHORT).show()
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