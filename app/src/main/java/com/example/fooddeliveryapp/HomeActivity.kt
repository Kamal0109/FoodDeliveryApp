package com.example.fooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.adapter.CategoryAdapter
import com.example.fooddeliveryapp.adapter.FastDeliveryAdapter
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerviewCategory = findViewById<RecyclerView>(R.id.view1)
        recyclerviewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataCategory = mutableListOf<CategoryDomain>()
        dataCategory.add(CategoryDomain("Category 1", R.drawable.cat_1))
        dataCategory.add(CategoryDomain("Category 2", R.drawable.cat_2))
        dataCategory.add(CategoryDomain("Category 3", R.drawable.cat_3))
        dataCategory.add(CategoryDomain("Category 4", R.drawable.cat_4))

        val adapterCategory = CategoryAdapter(dataCategory)
        recyclerviewCategory.adapter = adapterCategory

        // Initialize the fast delivery RecyclerView
        val recyclerviewFastDelivery = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerviewFastDelivery.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val dataFastDelivery = mutableListOf<FastDeliveryDomain>()
        dataFastDelivery.add(FastDeliveryDomain("Title 1", R.drawable.fast_1, 5.0, 10))
        dataFastDelivery.add(FastDeliveryDomain("Title 2", R.drawable.fast_2, 4.5, 12))
        dataFastDelivery.add(FastDeliveryDomain("Title 3", R.drawable.fast_3, 4.8, 8))

        val adapterFastDelivery = FastDeliveryAdapter(dataFastDelivery)
        recyclerviewFastDelivery.adapter = adapterFastDelivery
    }
}