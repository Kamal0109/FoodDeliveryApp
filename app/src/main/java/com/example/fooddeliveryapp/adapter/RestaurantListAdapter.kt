package com.example.fooddeliveryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.models.Hours
import com.example.fooddeliveryapp.models.RestaurantModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RestaurantListAdapter(val restaurantList : List<RestaurantModel?>?): RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListAdapter.MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_restaurant_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        return restaurantList?.size!!

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(restaurantList?.get(position))

    }
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val restaurantName : TextView= view.findViewById(R.id.restaurantName)
        val restaurantHours : TextView = view.findViewById(R.id.restaurantHours)
        val restaurantAddress : TextView = view.findViewById(R.id.restaurantAddress)
        val thumbImage : ImageView = view.findViewById(R.id.thumbImage)
        fun bind(restaurantModel: RestaurantModel?){
            restaurantName.text=restaurantModel?.name
            restaurantAddress.text="Address: "+restaurantModel?.address
            restaurantHours.text="Today's Hours: "+ getTodaysHours(restaurantModel?.hours!!)

            Glide.with(thumbImage)
                .load(restaurantModel?.image)
                .into(thumbImage)
        }
    }

    private fun getTodaysHours(hours: Hours):String?{
        val calendar:Calendar=Calendar.getInstance()
        val date:Date=calendar.time
        val day:String=SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
        return when(day){
            "Sunday"->hours.Sunday
            "Monday"->hours.Monday
            "Tuesday"->hours.Tuesday
            "Wednesday"->hours.Wednesday
            "Thursday"->hours.Thursday
            "Friday"->hours.Friday
            "Saturday"->hours.Saturday
            else->hours.Sunday
        }
    }


    interface RestaurantListClickListener{
        fun onItemClick(restaurantModel: RestaurantModel)
    }

}