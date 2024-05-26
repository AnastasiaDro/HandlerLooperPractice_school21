package com.school21.handlerlooper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var startButton: Button
    lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.button_start)
        stopButton = findViewById(R.id.button_stop)

        //запустим поток
        startButton.setOnClickListener {
            Log.d(TAG, "LooperThread started")
        }

        //остановим поток
        stopButton.setOnClickListener {
            Log.d(TAG, "LooperThread stopped")
        }
    }

}