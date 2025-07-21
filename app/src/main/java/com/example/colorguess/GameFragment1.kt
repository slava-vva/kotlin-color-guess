package com.example.colorguess

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.colorguess.databinding.ActivityMainBinding
import com.example.colorguess.databinding.FragmentGame1Binding
import kotlin.toString


class GameFragment1 : Fragment() {

    private lateinit var binding: FragmentGame1Binding

    private val colorMap = mapOf(
        "Red" to Color.RED,
        "Green" to Color.GREEN,
        "Blue" to Color.BLUE,
        "Yellow" to Color.YELLOW,
        "Cyan" to Color.CYAN,
        "Magenta" to Color.MAGENTA
    )

    private var correctColorName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentGame1Binding.inflate(inflater, container, false)

        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_game1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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