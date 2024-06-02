package com.school21.handlerlooper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.button_start)
        stopButton = findViewById(R.id.button_stop)
        text = findViewById(R.id.textView)

        //запустим поток
        val thread = LooperThread()
        val handler = CustomHandler(thread.looper)
        thread.start()

        var c = 0
        startButton.setOnClickListener {

            Log.d(TAG, "LooperThread started")
            var a =c + 10
            while (c < a) {
                handler.post(Message(handler, "I am desription $c") {
                    //handleMessage
                    println("${Thread.currentThread().name} maked runnable number $c")
                })
                c++
            }
        }

        //остановим поток
        stopButton.setOnClickListener {
            Log.d(TAG, "LooperThread stopped")
            thread.looper.quit()
        }

        text.setOnClickListener {
            text.postDelayed({ println("Hello world was pressed!!") }, 5000L)
        }
    }

}