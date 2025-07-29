package com.example.colorguess

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameLevel_2 : AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private lateinit var progressBar: ProgressBar
    private val gridSize = 4
    private val totalSquares = gridSize * gridSize

    private lateinit var db : DatabaseHelper
    private var userName : String = "Guest"
    private var attemptCount = 0
    private val maxAttempts = 5
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level2)

        val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        userName = prefs.getString("user_name", "Guest") ?: "Guest"
        db = DatabaseHelper(this)

        gridLayout = findViewById(R.id.gridLayout)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        setupGrid()

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun setupGrid() {
        gridLayout.removeAllViews()

        val baseColor = Color.rgb(
            (50..200).random(),
            (50..200).random(),
            (50..200).random()
        )

        val differentColor = adjustColor(baseColor, 1.15f) // 15% brighter

        val differentSquareIndex = (0 until totalSquares).random()

        for (i in 0 until totalSquares) {
            val square = View(this)
            square.layoutParams = ViewGroup.LayoutParams(150, 150)
            square.setBackgroundColor(if (i == differentSquareIndex) differentColor else baseColor)


            square.setOnClickListener {
                if (i == differentSquareIndex) {
                    Toast.makeText(this, "🎯 Correct!", Toast.LENGTH_SHORT).show()
                    score++

                } else {
                    Toast.makeText(this, "❌ Try Again!", Toast.LENGTH_SHORT).show()
                }
                attemptCount++
                progressBar.progress = attemptCount
                if (attemptCount >= maxAttempts) {
                    db.updateUserScore(userName, "score2", score)
                    showGameOverDialog()
                } else {
                    setupGrid() // Next round
                }
            }

            gridLayout.addView(square)
        }
    }

    private fun showGameOverDialog() {

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("🎉 Congratulations!")
        dialogBuilder.setMessage("Your score is $score.\nPlay again or go to next level?")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton("Play Again") { _, _ ->
            attemptCount = 0
            score = 0
            setupGrid() // Next round
        }
        dialogBuilder.setNegativeButton("Next Level") { _, _ ->
            // TODO: Go to next level
            finish()
        }
        dialogBuilder.show()
    }

    // Lighten or darken a color by a factor
    private fun adjustColor(color: Int, factor: Float): Int {
        val r = (Color.red(color) * factor).coerceIn(0f, 255f).toInt()
        val g = (Color.green(color) * factor).coerceIn(0f, 255f).toInt()
        val b = (Color.blue(color) * factor).coerceIn(0f, 255f).toInt()
        return Color.rgb(r, g, b)
    }
}