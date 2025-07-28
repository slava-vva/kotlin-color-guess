package com.example.colorguess

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.colorguess.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.textRegisterLink
        textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        // Navigate to Login screen
        binding.loginButton.setOnClickListener {
            Log.d("LoginActivity","onClick: LoginActivity")
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Navigate to Register screen
        binding.textRegisterLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Skip login and go directly to game
        binding.playButton.setOnClickListener {
            val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
            prefs.edit {
                putString("user_name", "Guest")
            }
            startActivity(Intent(this, MainActivity::class.java))
        }

//        binding.textView.setOnClickListener {
//            startActivity(Intent(this, GameActivity::class.java))
//        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}