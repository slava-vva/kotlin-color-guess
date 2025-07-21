package com.example.colorguess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.example.colorguess.databinding.ActivityGameBinding
import com.example.colorguess.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, GameFragment1()).commit()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnLevel1.setOnClickListener {
            fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container, GameFragment1()).commit()
        }

        binding.btnLevel2.setOnClickListener {
            fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container, FragmentColorCount()).commit()
        }

    }
}