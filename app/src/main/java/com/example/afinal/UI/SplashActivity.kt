package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.afinal.APIAuthentication.MyConfig
import com.example.afinal.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            if(checkLoggedIn()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }
        }, 3500)

    }

    private fun checkLoggedIn() : Boolean{
        // check if user is logged in
        val prefs = getSharedPreferences(
            MyConfig.SHARED_PREFS_FILENAME,
            MODE_PRIVATE
        )
        val token = prefs.getString("token", null) ?: return false
        return true
    }

}