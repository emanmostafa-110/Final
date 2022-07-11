package com.example.afinal.Alarm

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.afinal.R
import kotlinx.android.synthetic.main.activity_alarm_on_active.*

class AlarmOnActive : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_on_active)
        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarm)
        mediaPlayer.start()

        btn_stop.setOnClickListener {
            mediaPlayer.stop()
        }

    }
}