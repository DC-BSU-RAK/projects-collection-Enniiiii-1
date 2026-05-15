package com.example.skullgirls_codex

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)
        val subtitle = findViewById<TextView>(R.id.subtitle)
        val glow = findViewById<View>(R.id.glow)

        // Logo fade + scale animation
        logo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(1200)
            .start()

        // Subtitle fade
        subtitle.animate()
            .alpha(1f)
            .setDuration(1200)
            .setStartDelay(300)
            .start()

        // Glow pulse animation
        glow.animate()
            .alpha(0.8f)
            .setDuration(1500)
            .withEndAction {

                glow.animate()
                    .alpha(0.4f)
                    .setDuration(1500)
                    .start()
            }
            .start()

        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(
                Intent(this, InstructionActivity::class.java)
            )

            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

            finish()

        }, 2500)
    }
}