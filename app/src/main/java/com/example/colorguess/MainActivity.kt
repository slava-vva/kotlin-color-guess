package com.example.colorguess


import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.colorguess.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNewRound()

        val buttons = listOf(binding.button1, binding.button2, binding.button3)
        buttons.forEach { button ->
            button.setOnClickListener {
                val guess = button.text.toString()
                if (guess == correctColorName) {
                    binding.resultText.text = "🎉 Correct!"
                } else {
                    binding.resultText.text = "❌ Try again!"
                }
                binding.resultText.postDelayed({
                    setupNewRound()
                }, 1000)
            }
        }
    }

    private fun setupNewRound() {
        val colorNames = colorMap.keys.shuffled()
        correctColorName = colorNames[0]
        val options = colorNames.take(3).shuffled()

        binding.colorView.setBackgroundColor(colorMap[correctColorName] ?: Color.GRAY)

        binding.button1.text = options[0]
        binding.button2.text = options[1]
        binding.button3.text = options[2]

        binding.resultText.text = ""
    }
}
