package com.example.colorguess

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorguess.databinding.ActivityMongoDbApiBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MongoDbApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMongoDbApiBinding
    private lateinit var adapter: MongoCarAdapter
    private val carList = mutableListOf<MongoCar>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_mongo_db_api)

        binding = ActivityMongoDbApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MongoCarAdapter(carList,
            onDelete = { deleteCar(it) },
            onEdit = { fillForm(it) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener { addCar() }

        binding.btnLoad.setOnClickListener { loadCars() }

        loadCars()

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun loadCars() {
        println("started loadCars()")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                println("launched loadCars()")
                val cars = CarRepository.getCars()
                withContext(Dispatchers.Main) {
                    carList.clear()
                    carList.addAll(cars)
                    adapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                println("error loadCars()")
                showToast("Error: ${e.message}")
            }
        }
    }

    private fun addCar() {
        val brand = binding.editBrand.text.toString()
        val model = binding.editModel.text.toString()
        val year = binding.editYear.text.toString().toIntOrNull() ?: return

        val car = MongoCar(brand = brand, model = model, year = year)
        println("start adding")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                println("start adding 2")
                CarRepository.addCar(car)
                loadCars()
                println("start after load")
            } catch (e: Exception) {
                println("start ERROR load ${e.message}" )
                showToast("Add Error: ${e.message}")
            }
        }
    }

    private fun deleteCar(car: MongoCar) {
        car.id?.let {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    CarRepository.deleteCar(it)
                    loadCars()
                } catch (e: Exception) {
                    showToast("Delete Error: ${e.message}")
                }
            }
        }
    }

    private fun fillForm(car: MongoCar) {
        binding.editBrand.setText(car.brand)
        binding.editModel.setText(car.model)
        binding.editYear.setText(car.year.toString())
        // You can handle update logic with a button if needed
    }

    private fun showToast(msg: String) {
        runOnUiThread { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
    }

}