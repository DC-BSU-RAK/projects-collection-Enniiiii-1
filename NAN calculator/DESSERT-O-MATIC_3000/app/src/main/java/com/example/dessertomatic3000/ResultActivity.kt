package com.example.dessertomatic3000

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val popupContainer = findViewById<View>(R.id.popupContainer)
        popupContainer.alpha = 0f

        popupContainer.scaleX = 0.8f
        popupContainer.scaleY = 0.8f

        popupContainer.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(250)
            .start()

        window.setLayout(
            (resources.displayMetrics.widthPixels * 0.85).toInt(),
            (resources.displayMetrics.heightPixels * 0.65).toInt()
        )

        val resultTitle = findViewById<TextView>(R.id.resultTitle)
        val resultImage = findViewById<ImageView>(R.id.resultImage)
        val backButton = findViewById<Button>(R.id.backButton)

        val dessertName = intent.getStringExtra("DESSERT_NAME")

        resultTitle.text = dessertName

        when (dessertName) {

            "Chocolate Strawberry Oasis Pie!" -> {
                resultImage.setImageResource(R.drawable.chocolate_strawberry_oasis_pie)
            }

            "Chocolate Coconut Swirls!" -> {
                resultImage.setImageResource(R.drawable.chocolate_coconut_swirls)
            }

            "Chocolate Coffee Ice Cream!" -> {
                resultImage.setImageResource(R.drawable.chocolate_coffee_icecream)
            }

            "Strawberry Coconut Pudding!" -> {
                resultImage.setImageResource(R.drawable.strawberry_coconut_pudding)
            }

            "Strawberry Latte!" -> {
                resultImage.setImageResource(R.drawable.strawberry_latte)
            }

            "Coconut Coffee Panna Cotta!" -> {
                resultImage.setImageResource(R.drawable.coffee_coconut_panna_cotta)
            }

            else -> {
                resultImage.setImageResource(R.drawable.mystery_dessert)
            }
        }

        backButton.setOnClickListener {
            animateButtonPress(backButton)
            finish()
        }
    }

    private fun animateButtonPress(button: View) {

        button.animate()
            .scaleX(0.9f)
            .scaleY(0.9f)
            .setDuration(80)
            .withEndAction {

                button.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(80)
                    .start()
            }
            .start()
    }
}