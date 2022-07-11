package com.example.afinal.Alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import com.example.afinal.R

class Receive : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        var intent =Intent(p0,AlarmOnActive::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        p0?.startActivity(intent)
    }

}