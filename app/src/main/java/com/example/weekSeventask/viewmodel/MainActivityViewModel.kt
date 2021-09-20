package com.example.weekSeventask.viewmodel


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weekSeventask.PokeDataClass.Pokemon
import com.example.weekSeventask.jsonDataFile.DetailsForPokemon
import com.example.weekSeventask.network.RetroDetails
import com.example.weekSeventask.network.RetroForDetails
import com.example.weekSeventask.network.RetroInstance
import com.example.weekSeventask.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainActivityViewModel: ViewModel()  {
    var recyclerListLiveData: MutableLiveData<Pokemon> = MutableLiveData()
    var singlePokemonDetails: MutableLiveData<DetailsForPokemon> = MutableLiveData()

    // this is the function that pick the list of item we have in our data class which inherit from mutablelivedata
    fun getRecyclerListObserver(): MutableLiveData<Pokemon> {
        return recyclerListLiveData
    }
//
//    fun getRecyclerListDetails(): MutableLiveData<DetailsForPokemon>{
//       return singlePokemonDetails
//    }
    // the function that fetch the data from api
    fun makeApiCall(getID:Int){
        viewModelScope.launch(Dispatchers.IO) {
             try{
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)

            val response = retroInstance.getDataFromApi(0, getID)
//
                 getRecyclerListObserver().postValue(response)
//            if (response.isSuccessful){
//                recyclerListLiveData.postValue(response)
//            }else{
//             //   Toast.makeText(this@MainActivityViewModel,"There is no internet", Toast.LENGTH_SHORT).show()!!
//            }

        }catch (e:UnknownHostException){
            e.printStackTrace()

             }
        }

    }
    // this is the function that get the details of each pokemonDetails
    fun callPokimonDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val retroDetails = RetroDetails.getRetroDetails().create(RetroForDetails::class.java)
            val responses = retroDetails.getDataForDetails(id)
            singlePokemonDetails.postValue(responses)
        }

    }
}