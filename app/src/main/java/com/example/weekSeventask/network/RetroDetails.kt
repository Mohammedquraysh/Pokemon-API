package com.example.weekSeventask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroDetails {
    companion object{
    val detailsURL = "https://pokeapi.co/api/v2/"
    fun getRetroDetails(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(detailsURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    }
}