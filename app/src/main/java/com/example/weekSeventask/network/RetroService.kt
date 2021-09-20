package com.example.weekSeventask.network


import com.example.weekSeventask.PokeDataClass.Pokemon
import retrofit2.http.GET
import retrofit2.http.Query
// this is the interface that allow us to set the number of pokemon images
interface RetroService {
    @GET("pokemon")
    suspend fun getDataFromApi(@Query("offset") query: Int, @Query("limit") lim: Int): Pokemon


}