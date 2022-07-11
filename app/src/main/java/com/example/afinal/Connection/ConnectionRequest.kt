package com.example.afinal.Connection

import android.content.Intent
import android.opengl.Visibility
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
import com.example.afinal.Information.Alarm
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
import kotlinx.android.synthetic.main.activity_history_connection.*
import kotlinx.android.synthetic.main.activity_seizure_history.*
import kotlinx.android.synthetic.main.activity_seizure_history.rv_list_request
import org.json.JSONObject
import java.util.*
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
                                Toast.makeText(this@ConnectionRequest,
                                    "${response.getJSONObject(position).getInt("id")}",
                                Toast.LENGTH_LONG).show()
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




}