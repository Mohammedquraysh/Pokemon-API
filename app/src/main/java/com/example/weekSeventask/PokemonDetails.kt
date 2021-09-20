package com.example.weekSeventask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weekSeventask.jsonDataFile.DetailsForPokemon
import com.example.weekSeventask.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso

class PokemonDetails : AppCompatActivity() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    lateinit var name: TextView
    lateinit var pokeAbility: TextView
    lateinit var height: TextView
    private lateinit var card : CardView
    private lateinit var card1 : CardView
    private lateinit var errorMessage: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        pokeAbility = findViewById(R.id.ability)
        name = findViewById(R.id.name)
        height = findViewById(R.id.sprite)
        card = findViewById(R.id.card2)
        card1 = findViewById(R.id.card1)
        errorMessage =findViewById(R.id.tvError2)
        connectivityLiveData = ConnectivityLiveData(application)

      val newId = intent.getIntExtra("id", 1)

       val id = newId + 1

        Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
      apiCall(id.toString())


    }

// function to get the details for each pokemon
    private fun bind(it: DetailsForPokemon){

        name.text = getString(R.string.names, it.name)
        pokeAbility.text= getString(R.string.abilities, it.abilities[0].ability.name.capitalize(),
            it.abilities[0].is_hidden.toString(), it.abilities[0].slot.toString(), it.abilities[0].ability.name.capitalize(),
            it.abilities[0].is_hidden.toString(), it.abilities[0].slot.toString(), it.base_experience.toString())

        //   pokeAbility.text = it.abilities[0].ability.name.capitalize()



         height.text = getString(R.string.statistics, it.game_indices[0].game_index.toString(),
                        it.height.toString(), it.id.toString(),
                        it.moves[0].move.name.capitalize(), it.moves[1].move.name.capitalize(), it.moves[2].move.name.capitalize(),
                        it.moves[3].move.name.capitalize(), it.moves[4].move.name.capitalize(), it.moves[0].move.name.capitalize(),
                        it.moves[2].move.name.capitalize(), it.moves[4].move.name.capitalize(), it.moves[1].move.name.capitalize(),
                        it.moves[3].move.name.capitalize(), it.stats[0].base_stat.toString(), it.stats[0].effort.toString(),
                        it.stats[0].stat.name,
                        it.stats[1].base_stat.toString(), it.stats[1].effort.toString(), it.stats[1].stat.name,
                        it.stats[2].base_stat.toString(), it.stats[2].effort.toString(),
                        it.stats[2].stat.name, it.stats[3].base_stat.toString(), it.stats[3].effort.toString(), it.stats[3].stat.name,
                        it.stats[4].base_stat.toString(),
                        it.stats[4].effort.toString(), it.stats[4].stat.name, it.stats[5].base_stat.toString(),
                        it.stats[5].effort.toString(), it.stats[5].stat.name,
                        it.types[0].slot.toString(), it.types[0].type.name, it.weight.toString())


        val sprite = findViewById<ImageView>(R.id.imageView)
        val sprite2 = findViewById<ImageView>(R.id.imageView2)
        val firstUrlBack = it.sprites.front_default
        val secondUrlBack = it.sprites.back_default
        Picasso.get()
            .load(firstUrlBack)
            .into(sprite)
        Picasso.get()
            .load(secondUrlBack)
            .into(sprite2)
    }


// making Api call for get the details of each pokemon
    private fun apiCall(id: String):MainActivityViewModel{
        val viewModelTwo = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModelTwo.singlePokemonDetails.observe(this, Observer {
            if (it != null){
                bind(it)
                Toast.makeText(this,"Successful", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        })

//    viewModelTwo.callPokimonDetails(id)
    connectivityLiveData.observe(this, Observer { isAvailable ->

        when (isAvailable) {

            true -> {


                viewModelTwo.callPokimonDetails(id)
                errorMessage.visibility = View.GONE
                card.visibility = View.VISIBLE
                card1.visibility = View.VISIBLE
                height.visibility = View.VISIBLE
               pokeAbility.visibility = View.VISIBLE
                name.visibility = View.VISIBLE
              //  recycler.visibility = View.VISIBLE
//                    searchEditText.visibility = View.VISIBLE
            }
            false -> {

//                recycler.visibility = View.GONE
              errorMessage.visibility = View.VISIBLE
                card.visibility = View.GONE
                height.visibility = View.GONE
                pokeAbility.visibility = View.GONE
                card1.visibility = View.GONE
                name.visibility = View.GONE
////                    statusButton.visibility = View.VISIBLE
////                    moviesRecyclerView.visibility = View.GONE
////                    searchEditText.visibility = View.GONE
            }
        }
    })

    return viewModelTwo

    }
}