package com.example.dessertomatic3000

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_help)

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
            (resources.displayMetrics.heightPixels * 0.70).toInt()
        )

        val backButton = findViewById<Button>(R.id.helpBackButton)

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