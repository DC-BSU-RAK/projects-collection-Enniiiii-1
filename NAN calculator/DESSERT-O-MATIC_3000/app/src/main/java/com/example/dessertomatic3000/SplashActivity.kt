package com.example.dessertomatic3000

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val splashImage = findViewById<ImageView>(R.id.splashImage)
        val splashText = findViewById<TextView>(R.id.splashText)

        splashImage.scaleX = 0.8f
        splashImage.scaleY = 0.8f

        splashImage.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1200)
            .start()

        splashText.animate()
            .alpha(1f)
            .setStartDelay(700)
            .setDuration(800)
            .start()

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()

        }, 2500)
    }
}