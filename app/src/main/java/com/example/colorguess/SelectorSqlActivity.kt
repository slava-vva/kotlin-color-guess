package com.example.colorguess

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivitySelectorSqlBinding

class SelectorSqlActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectorSqlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySelectorSqlBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonSQL.setOnClickListener {
            startActivity(Intent(this, UsingMsSqlActivty ::class.java))
        }

        binding.buttonMongo.setOnClickListener {
            startActivity(Intent(this, PlayVideoActivity ::class.java))
        }

        binding.buttonAPI.setOnClickListener {
            startActivity(Intent(this, ForecastApiActivity ::class.java))
        }

        binding.buttonAPISQL.setOnClickListener {
            startActivity(Intent(this, PlayVideoActivity ::class.java))
        }

    }
}