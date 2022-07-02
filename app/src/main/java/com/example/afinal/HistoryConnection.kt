package com.example.afinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class HistoryConnection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_connection)
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
            R.id.seizureHistory->{
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