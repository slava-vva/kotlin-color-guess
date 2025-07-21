package com.example.colorguess

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivitySelectorBinding

class SelectorActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectorBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        //setContentView(R.layout.activity_selector)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSelectGame.setOnClickListener {
            startActivity(Intent(this, StartActivity ::class.java))
        }

        binding.buttonSelectFeatures.setOnClickListener {
            startActivity(Intent(this, FeaturesActivity ::class.java))
        }

        binding.buttonSelectWidgets.setOnClickListener {
            startActivity(Intent(this, WidgetsActivity ::class.java))
        }

    }
}