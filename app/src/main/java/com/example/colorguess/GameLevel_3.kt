package com.example.colorguess

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.Pair

class GameLevel_3 : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private lateinit var feedbackText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnBack: Button
    private lateinit var db: DatabaseHelper
    private var userName: String = "Guest"
    private var attemptCount = 0
    private val maxAttempts = 5
    private var score = 0

    private val testItems: List<Pair<Int, String>> = listOf(
        Pair(R.drawable.plate_12, "12"),
        Pair(R.drawable.plate_2, "2"),
        Pair(R.drawable.plate_27, "27"),
        Pair(R.drawable.plate_42, "42"),
        Pair(R.drawable.plate_74, "74"),
        Pair(R.drawable.plate_6, "6"),
        Pair(R.drawable.plate_45, "45"),
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_level3)

        imageView = findViewById(R.id.testImage)
        editText = findViewById(R.id.userInput)
        feedbackText = findViewById(R.id.feedbackText)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        btnBack = findViewById<Button>(R.id.btnBack)

        val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        userName = prefs.getString("user_name", "Guest") ?: "Guest"
        db = DatabaseHelper(this)

        val submitBtn = findViewById<Button>(R.id.submitBtn)

        loadTestImage()

        submitBtn.setOnClickListener {
            val answer = editText.text.toString().trim()
            val correctAnswer = testItems[currentIndex].second

            if (answer == correctAnswer) {
                score++
                feedbackText.text = "✅ Correct!"
            } else {
                feedbackText.text = "❌ Incorrect! Correct answer was $correctAnswer"
            }

            attemptCount++
            progressBar.progress = attemptCount
            if (attemptCount >= maxAttempts) {
                db.updateUserScore(userName, "score3", score)
                showGameOverDialog()
            } else {
                loadTestImage() // Next round
            }
        }

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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
            loadTestImage() // Next round
        }
        dialogBuilder.setNegativeButton("Next Level") { _, _ ->
            // TODO: Go to next level
            finish()
        }
        dialogBuilder.show()
    }

    private fun loadTestImage() {
        currentIndex = (0..6).random()
        val imgResId = testItems[currentIndex].first
        imageView.setImageResource(imgResId)
    }
}