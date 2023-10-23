package com.example.fooddeliveryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.CategoryDomain
import com.example.fooddeliveryapp.R

class CategoryAdapter(private val category: List<CategoryDomain>):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val categoryTitle: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPic: ImageView = itemView.findViewById(R.id.categoryPic)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        val categoryItem = category[position]

        // Bind data to the ViewHolder's elements
        holder.categoryTitle.text = categoryItem.title
        holder.categoryPic.setImageResource(categoryItem.pic)

    }

}