package com.example.weekSeventask

import com.example.weekSeventask.PokeDataClass.Pokemon
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weekSeventask.PokeDataClass.Result
import com.example.weekSeventask.adapter.RecyclerViewAdapter
import com.example.weekSeventask.viewmodel.MainActivityViewModel
import kotlin.properties.Delegates

class RecyclerListFragment : Fragment() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var searchButton: Button
    private lateinit var searchId: EditText
    private lateinit var msgError: TextView
   private lateinit var recycler: RecyclerView
  //  private var getID by Delegates.notNull<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_recycler_list, container, false)
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        searchButton = view.findViewById(R.id.button2)
        searchId = view.findViewById(R.id.searchID)
       recycler = view.findViewById(R.id.recyclerView)
        msgError = view.findViewById(R.id.tvError)
        var getID = 200
         myViewModel(getID)

        initViewModel(view)
        searchButton.setOnClickListener {

            getID =searchId.text.toString().toInt()


             if(getID==0){
                 Toast.makeText(activity, "press valid number", Toast.LENGTH_SHORT).show()
             } else{
                 myViewModel(getID)
             }



        }

        return view
    }




    // this is the function that connect our recyclerview adapter with and listener when an image is clicked
    private fun initViewModel(view: View,){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.setOnItemClickListener(object: RecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, value: View?) {
                val id = position
              //Toast.makeText(activity,"item $id was clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, PokemonDetails::class.java)

                intent.putExtra("id", id)
                startActivity(intent)
            }


        })







    }


    // this is where we observe the API call
    private fun myViewModel(getID: Int){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<Pokemon> {
            if (it != null){
                recyclerAdapter.setUpdatedData(it.results as ArrayList<Result>)

            }else {
                Toast.makeText(activity,"Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(getID)
        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    //3

//                    mainViewModel.onFragmentReady()
//                    statusButton.visibility = View.GONE
                 recycler.visibility = View.VISIBLE
                    msgError.visibility = View.GONE
//                    searchEditText.visibility = View.VISIBLE
                  }
                false -> {

                   recycler.visibility = View.GONE
                    msgError.visibility = View.VISIBLE
//                    statusButton.visibility = View.VISIBLE
//                    moviesRecyclerView.visibility = View.GONE
//                    searchEditText.visibility = View.GONE
                }
            }
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }
//    fun equalZero(): Boolean{
//        if(getID == 0){
//            Toast.makeText(activity, "press valid number", Toast.LENGTH_SHORT).show()
//            //myViewModel(getID)
//        }
//        return true
//    }
}