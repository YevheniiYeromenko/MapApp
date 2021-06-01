package com.yeromenko.mapapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yeromenko.mapapp.R
import com.yeromenko.mapapp.model.Place

class Adapter : RecyclerView.Adapter<Adapter.PlaceHolder>() {

    private var places : MutableList<Place> = ArrayList()
    private lateinit var context:Context

    fun setList(list: MutableList<Place>){
        places = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        context = parent.context
        return PlaceHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.txtPlaceName?.text = context.resources.getString(R.string.item_rv_places, places[position].id, places[position].name)
        if (position == (places.size - 1)) holder.divider?.visibility = View.GONE
    }

    override fun getItemCount(): Int = places.size


    inner class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtPlaceName: TextView ?= null
        var divider: View ?= null
        init {
            txtPlaceName = itemView.findViewById(R.id.txtPlaceName)
            divider = itemView.findViewById(R.id.divider)
        }
    }
}
