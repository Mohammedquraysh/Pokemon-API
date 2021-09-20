package com.example.weekSeventask.network

import com.example.weekSeventask.jsonDataFile.DetailsForPokemon
import retrofit2.http.GET
import retrofit2.http.Path

// this is the Interface for API
interface RetroForDetails {
    @GET("pokemon/{id}")
    suspend fun getDataForDetails(@Path("id") id: String): DetailsForPokemon
}