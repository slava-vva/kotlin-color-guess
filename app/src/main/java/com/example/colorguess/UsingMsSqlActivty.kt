package com.example.colorguess

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorguess.databinding.ActivityMainBinding
import com.example.colorguess.databinding.ActivityUsingMsSqlActivtyBinding


class UsingMsSqlActivty : AppCompatActivity() {
    private lateinit var binding: ActivityUsingMsSqlActivtyBinding
    private lateinit var adapter: CarAdapter
    private var selectedCar: Car? = null
    private val carList = mutableListOf<Car>()
    private lateinit var dbHelper: DatabaseSQLHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsingMsSqlActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseSQLHelper() // Replace with your working implementation

        adapter = CarAdapter(carList) { car ->
            selectedCar = car
            binding.editMake.setText(car.make)
            binding.editModel.setText(car.model)
            binding.editYear.setText(car.year.toString())
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener { addCar() }
        binding.btnUpdate.setOnClickListener { updateCar() }
        binding.btnDelete.setOnClickListener { deleteCar() }

        loadCars()
    }

    private fun loadCars() {
        Thread {
            val cars = CarsRepository.getAll()
            runOnUiThread {
                carList.clear()
                carList.addAll(cars)
                adapter.updateList(carList)
            }
        }.start()
    }

    private fun addCar() {
        val make = binding.editMake.text.toString()
        val model = binding.editModel.text.toString()
        val year = binding.editYear.text.toString().toIntOrNull()

        if (make.isEmpty() || model.isEmpty() || year == null) {
            showToast("Please fill all fields")
            return
        }

        Thread {
            val newCar = Car(id = 0, make = make, model = model, year = year)
            CarsRepository.insert(newCar)
            runOnUiThread {
                clearInputs()
                loadCars()
            }
        }.start()
    }

    private fun updateCar() {
        val car = selectedCar ?: return showToast("Select a car to update")
        val make = binding.editMake.text.toString()
        val model = binding.editModel.text.toString()
        val year = binding.editYear.text.toString().toIntOrNull()

        if (make.isEmpty() || model.isEmpty() || year == null) {
            showToast("Please fill all fields")
            return
        }

        Thread {
            val newCar = Car(id = 0, make = make, model = model, year = year)
            CarsRepository.update(newCar)
            runOnUiThread {
                clearInputs()
                loadCars()
            }
        }.start()
    }

    private fun deleteCar() {
        val car = selectedCar ?: return showToast("Select a car to delete")

        Thread {
            CarsRepository.delete(car.id)
            runOnUiThread {
                clearInputs()
                loadCars()
            }
        }.start()
    }

    private fun clearInputs() {
        binding.editMake.text.clear()
        binding.editModel.text.clear()
        binding.editYear.text.clear()
        selectedCar = null
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}