package com.example.afinal.Connection


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.Adapter.ConnectionAdapter
import com.example.afinal.Models.DoctorData
import com.example.afinal.R
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
                                val intent = Intent(this@ConnectionRequest,
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

}

