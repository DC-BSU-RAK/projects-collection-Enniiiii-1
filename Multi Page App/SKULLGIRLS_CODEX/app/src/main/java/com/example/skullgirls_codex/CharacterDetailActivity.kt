package com.example.skullgirls_codex

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val backButton = findViewById<Button>(R.id.backButton)
        val image = findViewById<ImageView>(R.id.detailImage)
        val name = findViewById<TextView>(R.id.detailName)
        val difficulty = findViewById<TextView>(R.id.detailDifficulty)
        val style = findViewById<TextView>(R.id.detailStyle)
        val bio = findViewById<TextView>(R.id.detailBio)

        val fighterName = intent.getStringExtra("name")
        val fighterBio = intent.getStringExtra("bio")
        val fighterDifficulty = intent.getStringExtra("difficulty")
        val fighterStyle = intent.getStringExtra("style")
        val fighterImage = intent.getIntExtra("image", 0)

        backButton.setOnClickListener {
            finish()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }

        name.text = fighterName
        difficulty.text = "Difficulty: $fighterDifficulty"
        style.text = "Style: $fighterStyle"
        bio.text = fighterBio
        image.setImageResource(fighterImage)

    }
}