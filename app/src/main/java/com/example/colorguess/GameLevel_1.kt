package com.example.colorguess

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityGameBinding
import com.example.colorguess.databinding.ActivityGameLevel1Binding
import com.example.colorguess.databinding.ActivityMainBinding

class GameLevel_1 : AppCompatActivity() {

    private lateinit var binding: ActivityGameLevel1Binding

    private var attemptCount = 0
    private val maxAttempts = 5

    private var score = 0
    private lateinit var dbHelper: DatabaseHelper
    private var userName: String = "Guest"

    private val colorMap = mapOf(
        "Red" to Color.RED,
        "Green" to Color.GREEN,
        "Blue" to Color.BLUE,
        "Yellow" to Color.YELLOW,
        "Cyan" to Color.CYAN,
        "Magenta" to Color.MAGENTA
    )

    private var correctColorName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityGameLevel1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        userName = prefs.getString("user_name", "Guest") ?: "Guest"
        dbHelper = DatabaseHelper(this)

        setupNewRound()

        val buttons = listOf(binding.button1, binding.button2, binding.button3)
        buttons.forEach {
                button ->
            button.setOnClickListener {
                val guess = button.text.toString()
                attemptCount++
                binding.progressBar.progress = attemptCount

                if (guess == correctColorName) {
                    score += 1
                    binding.resultText.text = "🎉 Correct!"
                } else {
                    binding.resultText.text = "❌ Try again!"
                }

                if (attemptCount >= maxAttempts) {
                    showGameOverDialog()
                } else {
                    binding.resultText.postDelayed({
                        setupNewRound()
                    }, 1000)
                }
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun showGameOverDialog() {
        dbHelper.updateUserScore(userName, score)

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("🎉 Congratulations!")
        dialogBuilder.setMessage("Your score is $score.\nPlay again or go to next level?")
        dialogBuilder.setCancelable(false)
        dialogBuilder.setPositiveButton("Play Again") { _, _ ->
            attemptCount = 0
            score = 0
            setupNewRound()
        }
        dialogBuilder.setNegativeButton("Next Level") { _, _ ->
            // TODO: Go to next level
            finish()
        }
        dialogBuilder.show()
    }

    private fun setupNewRound() {
        val colorNames = colorMap.keys.shuffled()
        correctColorName = colorNames[0]
        val options = colorNames.take(3).shuffled()

        val shapeTypes = listOf("circle", "square", "triangle", "star")
        val selectedShape = shapeTypes.random()
        val selectedColor = colorMap[correctColorName] ?: Color.GRAY

        when (selectedShape) {
            "circle" -> {
                val drawable = ContextCompat.getDrawable(this, R.drawable.circle_shape)!!.mutate()
                (drawable as GradientDrawable).setColor(selectedColor)
                binding.shapeView.setImageDrawable(drawable)
            }
            "square" -> {
                val drawable = ContextCompat.getDrawable(this, R.drawable.square_shape)!!.mutate()
                (drawable as GradientDrawable).setColor(selectedColor)
                binding.shapeView.setImageDrawable(drawable)
            }
            "triangle" -> {
                val drawable = ContextCompat.getDrawable(this, R.drawable.triangle_shape)!!.mutate()
                drawable.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                binding.shapeView.setImageDrawable(drawable)
            }
            "star" -> {
                val drawable = ContextCompat.getDrawable(this, R.drawable.star_shape)!!.mutate()
                drawable.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                binding.shapeView.setImageDrawable(drawable)
            }
        }

        binding.button1.text = options[0]
        binding.button2.text = options[1]
        binding.button3.text = options[2]

        binding.resultText.text = ""
    }
}
