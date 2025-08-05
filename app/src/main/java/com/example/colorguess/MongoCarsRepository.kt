package com.example.colorguess

object CarRepository {
    private val api = RetrofitClient.api

    suspend fun getCars() = api.getCars()
    suspend fun addCar(car: MongoCar) = api.addCar(car)
    suspend fun updateCar(id: String, car: MongoCar) = api.updateCar(id, car)
    suspend fun deleteCar(id: String) = api.deleteCar(id)
}