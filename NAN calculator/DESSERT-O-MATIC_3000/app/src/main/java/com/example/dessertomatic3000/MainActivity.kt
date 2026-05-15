package com.example.dessertomatic3000

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var chocoButton: ImageButton
    private lateinit var strawberryButton: ImageButton
    private lateinit var coffeeButton: ImageButton
    private lateinit var coconutButton: ImageButton
    private lateinit var selectText: TextView

    private var firstIngredient = ""
    private var secondIngredient = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chocoButton = findViewById(R.id.chocoButton)
        strawberryButton = findViewById(R.id.strawberryButton)
        coffeeButton = findViewById(R.id.coffeeButton)
        coconutButton = findViewById(R.id.coconutButton)

        floatingAnimation(chocoButton, 12f)
        floatingAnimation(strawberryButton, 16f)
        floatingAnimation(coffeeButton, 10f)
        floatingAnimation(coconutButton, 14f)

        val createButton = findViewById<Button>(R.id.createButton)

        val helpButton = findViewById<Button>(R.id.helpButton)

        val clearButton = findViewById<Button>(R.id.clearButton)


        helpButton.setOnClickListener {
            animateButtonPress(helpButton)

            val intent = Intent(this, HelpActivity::class.java)

            startActivity(intent)
        }

        clearButton.setOnClickListener {
            animateButtonPress(clearButton)
            clearSelections()
        }

        selectText = findViewById(R.id.selectText)

        chocoButton.setOnClickListener {
            selectIngredient("Chocolate", chocoButton)
        }

        strawberryButton.setOnClickListener {
            selectIngredient("Strawberry", strawberryButton)
        }

        coffeeButton.setOnClickListener {
            selectIngredient("Coffee", coffeeButton)
        }

        coconutButton.setOnClickListener {
            selectIngredient("Coconut", coconutButton)
        }

        createButton.setOnClickListener {
            createDessert()
            animateButtonPress(createButton)
        }
    }
    private fun floatingAnimation(button: ImageButton, distance: Float) {

        button.animate()
            .translationY(-distance)
            .setDuration(1200)
            .withEndAction {

                button.animate()
                    .translationY(0f)
                    .setDuration(1200)
                    .withEndAction {

                        if (button.alpha == 1.0f) {

                            floatingAnimation(button, distance)
                        }
                    }
                    .start()
            }
            .start()
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
    private fun shakeButton(button: ImageButton) {

        button.animate()
            .translationX(15f)
            .setDuration(50)
            .withEndAction {

                button.animate()
                    .translationX(-15f)
                    .setDuration(50)
                    .withEndAction {

                        button.animate()
                            .translationX(0f)
                            .setDuration(50)
                            .start()
                    }
                    .start()
            }
            .start()
    }
    private fun clearSelections() {

        firstIngredient = ""
        secondIngredient = ""

        chocoButton.alpha = 1.0f
        strawberryButton.alpha = 1.0f
        coffeeButton.alpha = 1.0f
        coconutButton.alpha = 1.0f


        chocoButton.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
        strawberryButton.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
        coffeeButton.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
        coconutButton.animate().scaleX(1f).scaleY(1f).setDuration(120).start()

        floatingAnimation(chocoButton, 12f)
        floatingAnimation(strawberryButton, 16f)
        floatingAnimation(coffeeButton, 10f)
        floatingAnimation(coconutButton, 14f)

        updateText()
    }
    private fun selectIngredient(

        ingredient: String,
        button: ImageButton
    ) {
        if (ingredient == firstIngredient || ingredient == secondIngredient) {

            shakeButton(button)

            return
        }
        if (firstIngredient.isEmpty()) {

            firstIngredient = ingredient

            button.alpha = 0.5f
            button.animate().cancel()

            button.translationY = 0f
            button.animate()
                .scaleX(1.15f)
                .scaleY(1.15f)
                .setDuration(120)
                .start()
        }
        else if (secondIngredient.isEmpty()) {

            secondIngredient = ingredient

            button.alpha = 0.5f
            button.animate().cancel()

            button.translationY = 0f
            button.animate()
                .scaleX(1.15f)
                .scaleY(1.15f)
                .setDuration(120)
                .start()
        }

        updateText()
    }

    private fun updateText() {

        if (firstIngredient.isEmpty() && secondIngredient.isEmpty()) {

            selectText.text = "Select an ingredient!"

        } else {

            selectText.text =
                "Selected:\n$firstIngredient\n$secondIngredient"
        }
    }

    private fun createDessert() {

        if (firstIngredient.isEmpty() || secondIngredient.isEmpty()) {

            selectText.text = "Please select two ingredients!!"

            return
        }

        val result = when {

            (firstIngredient == "Chocolate" && secondIngredient == "Strawberry") ||
                    (firstIngredient == "Strawberry" && secondIngredient == "Chocolate")
                -> "Chocolate Strawberry Oasis Pie!"

            (firstIngredient == "Chocolate" && secondIngredient == "Coffee") ||
                    (firstIngredient == "Coffee" && secondIngredient == "Chocolate")
                -> "Chocolate Coffee Ice Cream!"

            (firstIngredient == "Chocolate" && secondIngredient == "Coconut") ||
                    (firstIngredient == "Coconut" && secondIngredient == "Chocolate")
                -> "Chocolate Coconut Swirls!"

            (firstIngredient == "Strawberry" && secondIngredient == "Coconut") ||
                    (firstIngredient == "Coconut" && secondIngredient == "Strawberry")
                -> "Strawberry Coconut Pudding!"

            (firstIngredient == "Strawberry" && secondIngredient == "Coffee") ||
                    (firstIngredient == "Coffee" && secondIngredient == "Strawberry")
                -> "Strawberry Latte!"

            (firstIngredient == "Coconut" && secondIngredient == "Coffee") ||
                    (firstIngredient == "Coffee" && secondIngredient == "Coconut")
                -> "Coconut Coffee Panna Cotta!"

            else -> "Mystery Dessert"
        }

        val intent = Intent(this, ResultActivity::class.java)

        intent.putExtra("DESSERT_NAME", result)

        startActivity(intent)

        clearSelections()

        updateText()
    }
}