package com.example.colorguess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.colorguess.databinding.ActivityFragmentContainerBinding

class FragmentContainerActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityFragmentContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container, Fragment1()).commit()

        }

        binding.btnFragment2.setOnClickListener {
            fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_container, Fragment2()).commit()
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}