package com.example.colorguess

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.ListView
import android.widget.Toast
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.view.WindowInsetsCompat

class ListViewActivity : AppCompatActivity() {

    private lateinit var carListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list_view)

        carListView = findViewById(R.id.carListView)

        val carBrands = listOf(
            "Toyota", "Honda", "Ford", "BMW", "Mercedes",
            "Audi", "Tesla", "Nissan", "Hyundai", "Chevrolet"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, carBrands)
        carListView.adapter = adapter

        carListView.setOnItemClickListener { _, _, position, _ ->
            val selectedBrand = carBrands[position]
            Toast.makeText(this, "You selected: $selectedBrand", Toast.LENGTH_SHORT).show()
        }

        val btnBack = findViewById< Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }
}