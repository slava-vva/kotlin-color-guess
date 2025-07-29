package com.example.colorguess

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.colorguess.databinding.ActivityLoginBinding
import androidx.core.content.edit

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
//            Log.d("PAR", "par Email: $email")
            val password = binding.passwordInput.text.toString()
            val db = DatabaseHelper(this)
            val valid = db.loginUser(email, password)
            if (valid) {
                val userName = db.getUserNameByEmail(email)

                // Save to SharedPreferences
                val prefs = getSharedPreferences("UserSession", MODE_PRIVATE)
                prefs.edit {
                    putString("user_name", userName)
                }

                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Wrong credentials", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
