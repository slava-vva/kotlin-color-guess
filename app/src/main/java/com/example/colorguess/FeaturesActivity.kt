package com.example.colorguess

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.colorguess.databinding.ActivityFeaturesBinding
import androidx.core.net.toUri

class FeaturesActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFeaturesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFeaturesBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnPlayVideo.setOnClickListener {
            startActivity(Intent(this, PlayVideoActivity ::class.java))
        }

        binding.imageButtonPick.setOnClickListener {
            startActivity(Intent(this, PickImagesActivity ::class.java))
        }

        binding.btnGoogleMaps.setOnClickListener {
            startActivity(Intent(this, GoogleMapsActivity ::class.java))
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnBrowser.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, "https://spacenews.com/".toUri())
            startActivity(intent)
        }

        binding.btnDial.setOnClickListener {
            startActivity(Intent(this, DialActivity::class.java))

        }

        binding.btnSMS.setOnClickListener {
            startActivity(Intent(this, DialActivity::class.java))

        }

        binding.btnSendEmail.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))

        }

    }
}