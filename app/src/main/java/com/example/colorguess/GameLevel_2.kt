package com.example.colorguess

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameLevel_2 : AppCompatActivity() {
    private lateinit var gridLayout: GridLayout
    private val gridSize = 4
    private val totalSquares = gridSize * gridSize

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level2)

        gridLayout = findViewById(R.id.gridLayout)
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
                    setupGrid() // Next round
                } else {
                    Toast.makeText(this, "❌ Try Again!", Toast.LENGTH_SHORT).show()
                }
            }

            gridLayout.addView(square)
        }
    }

    // Lighten or darken a color by a factor
    private fun adjustColor(color: Int, factor: Float): Int {
        val r = (Color.red(color) * factor).coerceIn(0f, 255f).toInt()
        val g = (Color.green(color) * factor).coerceIn(0f, 255f).toInt()
        val b = (Color.blue(color) * factor).coerceIn(0f, 255f).toInt()
        return Color.rgb(r, g, b)
    }
}