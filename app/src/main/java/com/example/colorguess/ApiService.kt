package com.example.colorguess

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/cars/")
    suspend fun getCars(): List<MongoCar>

    @POST("api/cars/")
    suspend fun addCar(@Body car: MongoCar): MongoCar

    @PUT("api/cars/{id}/")
    suspend fun updateCar(@Path("id") id: String, @Body car: MongoCar): MongoCar

    @DELETE("api/cars/{id}/")
    suspend fun deleteCar(@Path("id") id: String): Response<Unit>
}