package com.example.fooddeliveryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.FastDeliveryDomain
import com.example.fooddeliveryapp.R

class FastDeliveryAdapter(private val fastDelivery : List<FastDeliveryDomain>):RecyclerView.Adapter<FastDeliveryAdapter.FastDeliveryViewHolder>(){

    inner class FastDeliveryViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val fastDeliveryTitle : TextView = itemView.findViewById(R.id.title)
        val star : TextView = itemView.findViewById(R.id.star)
        val fastDeliveryTime : TextView = itemView.findViewById(R.id.time)
        val fastDeliveryPic : ImageView = itemView.findViewById(R.id.fastDeliveryPic)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FastDeliveryAdapter.FastDeliveryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_fast_delivery, parent, false)
        return FastDeliveryViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: FastDeliveryAdapter.FastDeliveryViewHolder,
        position: Int
    ) {
        val fastDeliveryItem = fastDelivery[position]

        // Bind data to the ViewHolder's elements
        holder.fastDeliveryTitle.text = fastDeliveryItem.title
        holder.star.text = fastDeliveryItem.star.toString()
        holder.fastDeliveryTime.text = fastDeliveryItem.time.toString()
        holder.fastDeliveryPic.setImageResource(fastDeliveryItem.pic)


    }

    override fun getItemCount(): Int {
        return fastDelivery.size
    }
}