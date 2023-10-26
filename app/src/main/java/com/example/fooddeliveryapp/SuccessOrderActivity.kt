package com.example.fooddeliveryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.example.fooddeliveryapp.databinding.ActivityPlaceYourOrderBinding
import com.example.fooddeliveryapp.models.RestaurantModel

class SuccessOrderActivity : AppCompatActivity() {

    private lateinit var onBinding: ActivityPlaceYourOrderBinding
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBinding = ActivityPlaceYourOrderBinding.inflate(layoutInflater)
        val view = onBinding.root
        setContentView(view)

        val restaurantModel: RestaurantModel? = intent.getParcelableExtra("RestaurantModel")
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setTitle(restaurantModel?.name)
        actionbar?.setSubtitle(restaurantModel?.address)
        actionbar?.setDisplayHomeAsUpEnabled(false)

        textView = findViewById(R.id.buttonDeDone)

        textView.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

    }
}