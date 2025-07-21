package com.example.colorguess

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity
import com.example.colorguess.databinding.ActivityMainBinding
import com.example.colorguess.databinding.ActivityWidgetsBinding

class WidgetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWidgetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnShowFragnment.setOnClickListener {
            startActivity(Intent(this, FragmentContainerActivity::class.java))
        }

        binding.btnListView.setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        binding.btnControls.setOnClickListener {
            startActivity(Intent(this, ControlsActivity::class.java))
        }

    }
}