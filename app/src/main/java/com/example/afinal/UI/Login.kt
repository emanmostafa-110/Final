package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import com.example.afinal.APIAuthentication.MyConfig
import com.example.afinal.R
import com.example.finalseizures.MyRequest
import org.json.JSONObject

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_login= findViewById<Button>(R.id.btn_login)
        val noAccount= findViewById<TextView>(R.id.tv_registration)
        val forgetPassword= findViewById<TextView>(R.id.tv_forgetPassword)

        noAccount.setOnClickListener {
            val intent = Intent(this@Login, Registration::class.java)
            startActivity(intent)
        }

        forgetPassword.setOnClickListener {
            val intent = Intent(this@Login, ForgetPassword::class.java)
            startActivity(intent)
        }


        btn_login.setOnClickListener { loginClicked() }


    }
    private fun loginClicked(){

        val et_email= findViewById<EditText>(R.id.et_email)
        val et_password= findViewById<EditText>(R.id.et_password)
        //if(validateData()) {
        // data we send in the request: Email and password
        val params = JSONObject()
        params.put("email",et_email.text.toString())
        params.put("password", et_password.text.toString())

        Log.d("mytag", "Button clicked")

        // send request
        val queue = Volley.newRequestQueue(this)
        val request = MyRequest(
            this,
            Request.Method.POST,
            "/login",
            params,
            { response ->

                Log.d("mytag", "response = $response")

                // if token was sent
                if (response.has("token")) {
                    // store token in shared prefs
                    val token = response.getString("token")
                    val prefs = getSharedPreferences(
                        MyConfig.SHARED_PREFS_FILENAME,
                        MODE_PRIVATE
                    )
                    val prefsEditor = prefs.edit()
                    prefsEditor.putString("token", token)
                    prefsEditor.apply()
                    // goto profile activity
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

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
        //}
    }
}