package com.example.coroutineex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClicked = findViewById<Button>(R.id.btnClicked)
        val txtData = findViewById<TextView>(R.id.txtData)


        btnClicked.setOnClickListener{

                var sum = 0L
                var time = measureTime {
                    for(i in 1 .. 2_000_000_000_000){
                        sum += i
                    }
                }
                Log.d("test", "time : $time")
                txtData.text = "sum : $sum"
            }

    }
}