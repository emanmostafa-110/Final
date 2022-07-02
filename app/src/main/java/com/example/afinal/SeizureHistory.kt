package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class SeizureHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seizure_history)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menue, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this@SeizureHistory, MyProfile::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizureHistory->{
                val intent = Intent(this@SeizureHistory, SeizureHistory::class.java)
                startActivity(intent)
                return true
            }
            R.id.medicalRecord ->{
                val intent = Intent(this@SeizureHistory, MedicalRecord::class.java)
                startActivity(intent)
                return true
            }
            R.id.symptoms ->{
                val intent = Intent(this@SeizureHistory, Symptoms::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionHistory ->{
                val intent = Intent(this@SeizureHistory, HistoryConnection::class.java)
                startActivity(intent)
                return true
            }
            R.id.connectionRequest ->{
                val intent = Intent(this@SeizureHistory, ConnectionRequest::class.java)
                startActivity(intent)
                return true
            }
            R.id.alarm ->{
                val intent = Intent(this@SeizureHistory, Alarm::class.java)
                startActivity(intent)
                return true
            }
            R.id.diet ->{
                val intent = Intent(this@SeizureHistory, Diet::class.java)
                startActivity(intent)
                return true
            }
            R.id.seizure ->{
                val intent = Intent(this@SeizureHistory, SeizureInfo::class.java)
                startActivity(intent)
                return true
            }
            R.id.question ->{
                val intent = Intent(this@SeizureHistory, FrequentQuestion::class.java)
                startActivity(intent)
                return true
            }
            R.id.logout ->{
                val intent = Intent(this@SeizureHistory, Login::class.java)
                startActivity(intent)
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}