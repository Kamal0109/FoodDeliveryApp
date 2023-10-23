package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.adapter.RestaurantListAdapter
import com.example.fooddeliveryapp.models.RestaurantModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.io.StringWriter
import java.io.Writer
import java.lang.Exception

class RestaurantActivity : AppCompatActivity(),RestaurantListAdapter.RestaurantListClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        val restaurantModel = getRestaurantData()
        initRecyclerView(restaurantModel)
    }

    private fun initRecyclerView(restaurantList: List<RestaurantModel?>?){
        val recyclerViewRestaurant = findViewById<RecyclerView>(R.id.recyclerViewRestaurant)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        val adapter = RestaurantListAdapter(restaurantList)
        recyclerViewRestaurant.adapter=adapter
    }

    private fun getRestaurantData() : List<RestaurantModel?>?{
        val inputStream : InputStream = resources.openRawResource(R.raw.restaurant)
        val writer : Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream,"UTF-8"))
            var n : Int
            while(reader.read(buffer).also { n = it } != -1){
                writer.write(buffer,0,n)
            }
        }
        catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<RestaurantModel>>(jsonStr, Array<RestaurantModel>::class.java).toList()
        return restaurantModel
    }

    override fun onItemClick(restaurantModel: RestaurantModel) {
        val intent = Intent(this@RestaurantActivity,ReataurantMenuActivity::class.java)
        intent.putExtra("RestaurantModel",restaurantModel)
        startActivity(intent)
    }
}