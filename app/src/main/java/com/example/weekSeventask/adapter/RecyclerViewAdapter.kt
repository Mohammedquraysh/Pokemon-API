package com.example.weekSeventask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weekSeventask.Functions
import com.example.weekSeventask.R
import com.squareup.picasso.Picasso
import com.example.weekSeventask.PokeDataClass.Result

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var result = ArrayList<Result>()
    //private var ability = ArrayList<Ability>()
   // lateinit var listen: OnItemClickListener
    private lateinit var nListener: OnItemClickListener

    interface OnItemClickListener{

        fun onItemClick(position:Int,value: View?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        nListener = listener
    }
    fun setUpdatedData(result: ArrayList<Result>){
        this.result = result
        notifyDataSetChanged()

    }

//    fun setUpdatedDetails(ability: ArrayList<Ability>){
//        this.ability = ability
//        notifyDataSetChanged()
//    }
   inner class MyViewHolder(view: View, listener: OnItemClickListener ): RecyclerView.ViewHolder(view){
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
       // val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition,itemView)
            }
        }


        fun bind(data: Result){
            tvTitle.text = data.name
           val myUrl = data.url
          //  tvDesc.text = data.description


           val getImageNumber = Functions.getUrlNumber(myUrl)
           Picasso.get()
               .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${getImageNumber}.png")
                .into(imageThumb)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false )
        return MyViewHolder(view, nListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(result.get(position))
        //holder.imageThumb
    }

    override fun getItemCount(): Int {
        return result.size
    }
}