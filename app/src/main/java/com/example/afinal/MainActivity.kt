package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_uploadEEG= findViewById<Button>(R.id.uploadEeg)
        val btn_uploadEMG= findViewById<Button>(R.id.uploadEmg)
        val btn_recordEMG= findViewById<Button>(R.id.recordEmg)

        btn_recordEMG.setOnClickListener{
            val intent = Intent(this@MainActivity, RecordEMG::class.java)
            startActivity(intent)
        }

        btn_uploadEEG.setOnClickListener{
            val intent = Intent(this@MainActivity, UploadEEG::class.java)
            startActivity(intent)
        }

        btn_uploadEMG.setOnClickListener{
            val intent = Intent(this@MainActivity, UploadEMG::class.java)
            startActivity(intent)
        }
    }
}