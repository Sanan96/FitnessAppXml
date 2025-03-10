package com.example.newfit

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
private lateinit var timer:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        timer = object : CountDownTimer(2000, 1000){

            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}