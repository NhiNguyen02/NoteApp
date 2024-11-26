package com.nhom2.noteapp

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            startActivity(Intent(this, MainActivity::class.java))

        }, 2000)
    }

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {

            startActivity(Intent(this, MainActivity::class.java))

        }, 2000)


    }
}