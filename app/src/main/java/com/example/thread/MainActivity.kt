package com.example.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    var job1:Job?=null
    var job2:Job?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1:Button= findViewById(R.id.btn)
        val btn2:Button= findViewById(R.id.btn2)
        val can:Button= findViewById(R.id.can)
        val txt1:TextView=findViewById(R.id.txt1)
        val txt2:TextView=findViewById(R.id.txt2)
        btn1.setOnClickListener {
            var i=0
            job1 = GlobalScope.launch {
                while (i<=9) {
                    ecrireMessage(i,txt1)
                    i++
                }
            }
        }
        btn2.setOnClickListener {
            var j=10
            job2 = GlobalScope.async {
                 while (j<=20) {
                     ecrireMessage(j,txt2)
                     j++
                 }
            }
        }

        can.setOnClickListener{
            job1?.cancel()
            job2?.cancel()
            txt1.setText("-1")
            txt2.setText("-1")
        }

    }
    suspend fun ecrireMessage(i:Int,tex:TextView){
        delay(500L)
        tex.setText(i.toString())
    }
}