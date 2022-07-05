package com.example.afinal.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.example.afinal.R

class SuccessfulSend : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_send)

        val repasslottie =findViewById<LottieAnimationView>(R.id.repasslottie)

        repasslottie.visibility = View.VISIBLE
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
        },3000)

    }
}