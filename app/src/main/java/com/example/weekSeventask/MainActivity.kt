package com.example.weekSeventask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.weekSeventask.PokeDataClass.Result
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
  //  private lateinit var userRecyclerview: RecyclerView
  //  private lateinit var resultList: ArrayList<Result>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
    }

    // This is function that display on the transactions we have in our RecyclerList Fragment
    private fun setupFragment(){
        val fragment = RecyclerListFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content,fragment)
        fragmentTransaction.commit()
    }
}