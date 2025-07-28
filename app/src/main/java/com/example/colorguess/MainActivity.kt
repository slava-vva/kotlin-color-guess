package com.example.colorguess


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.colorguess.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHelper
    private var userName: String = "Guest"

    private lateinit var gameLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        userName = prefs.getString("user_name", "Guest") ?: "Guest"
        db = DatabaseHelper(this)

        val welcomeText = binding.textWelcome
        welcomeText.text = "Welcome, $userName!"

        var score_level1 = db.getUserScore(userName);
        gameLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            score_level1 = db.getUserScore(userName);
            binding.textScoreLevel1.text = "Level 1: $score_level1/5"
        }
        binding.textScoreLevel1.text = "Level 1: $score_level1/5"

        binding.buttonLevel1.setOnClickListener {
            //startActivity(Intent(this, GameLevel_1::class.java))
            val intent = Intent(this, GameLevel_1::class.java)
            gameLauncher.launch(intent)
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }



}
