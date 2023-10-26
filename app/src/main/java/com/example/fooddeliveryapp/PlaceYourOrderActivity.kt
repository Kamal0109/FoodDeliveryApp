package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryapp.adapter.PlaceYourOrderAdapter
import com.example.fooddeliveryapp.databinding.ActivityPlaceYourOrderBinding
import com.example.fooddeliveryapp.databinding.ActivityReataurantMenuBinding
import com.example.fooddeliveryapp.models.RestaurantModel

class PlaceYourOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlaceYourOrderBinding
    var placeYourOrderAdapter: PlaceYourOrderAdapter? = null
    var isDeliveryOn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceYourOrderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val restaurantModel : RestaurantModel? = intent.getParcelableExtra(RestaurantModel.toString())
        val actionbar: ActionBar? = supportActionBar
        actionbar?.setTitle(restaurantModel?.name)
        actionbar?.setSubtitle(restaurantModel?.address)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        binding.buttonPlaceYourOrder.setOnClickListener {

            onPlaceOrderButtonCLick(restaurantModel)

        }

        binding.switchDelivery?.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked) {
                binding.inputAddress.visibility = View.VISIBLE
                binding.inputCity.visibility = View.VISIBLE
                binding.inputState.visibility = View.VISIBLE
                binding.inputZip.visibility = View.VISIBLE
                binding.tvDeliveryCharge.visibility = View.VISIBLE
                binding.tvDeliveryChargeAmount.visibility = View.VISIBLE
                isDeliveryOn = true
                calculateTotalAmount(restaurantModel)
            } else {
                binding.inputAddress.visibility = View.GONE
                binding.inputCity.visibility = View.GONE
                binding.inputState.visibility = View.GONE
                binding.inputZip.visibility = View.GONE
                binding.tvDeliveryCharge.visibility = View.GONE
                binding.tvDeliveryChargeAmount.visibility = View.GONE
                isDeliveryOn = false
                calculateTotalAmount(restaurantModel)
            }
        }

        initRecyclerView(restaurantModel)
        calculateTotalAmount(restaurantModel)


    }

    private fun initRecyclerView(restaurantModel: RestaurantModel?) {
        binding.cartItemsRecyclerView.layoutManager = LinearLayoutManager(this)
        placeYourOrderAdapter = PlaceYourOrderAdapter(restaurantModel?.menus)
        binding.cartItemsRecyclerView.adapter = placeYourOrderAdapter
    }

    private fun calculateTotalAmount(restaurantModel: RestaurantModel?) {
        if (restaurantModel != null) {
            var subTotalAmount = 0f

            restaurantModel.menus?.let { menus ->
                for (menu in menus) {
                    subTotalAmount += menu?.price ?: 0f * (menu?.totalInCart ?: 0)
                }
            }

            binding.tvSubtotalAmount.text = "$" + String.format("%.2f", subTotalAmount)

            if (isDeliveryOn) {
                val deliveryCharge = restaurantModel.delivery_charge?.toFloat() ?: 0f
                binding.tvDeliveryChargeAmount.text = "$" + String.format("%.2f", deliveryCharge)
                subTotalAmount += deliveryCharge
            }

            binding.tvTotalAmount.text = "$" + String.format("%.2f", subTotalAmount)
        }
    }

    private fun onPlaceOrderButtonCLick(restaurantModel: RestaurantModel?) {
        if(TextUtils.isEmpty(binding.inputName.text.toString())) {
            binding.inputName.error =  "Enter your name"
            return
        } else if(isDeliveryOn && TextUtils.isEmpty(binding.inputAddress.text.toString())) {
            binding.inputAddress.error =  "Enter your address"
            return
        } else if(isDeliveryOn && TextUtils.isEmpty(binding.inputCity.text.toString())) {
            binding.inputCity.error =  "Enter your City Name"
            return
        } else if(isDeliveryOn && TextUtils.isEmpty(binding.inputZip.text.toString())) {
            binding.inputZip.error =  "Enter your Zip code"
            return
        } else if( TextUtils.isEmpty(binding.inputCardNumber.text.toString())) {
            binding.inputCardNumber.error =  "Enter your credit card number"
            return
        } else if( TextUtils.isEmpty(binding.inputCardExpiry.text.toString())) {
            binding.inputCardExpiry.error =  "Enter your credit card expiry"
            return
        } else if( TextUtils.isEmpty(binding.inputCardPin.text.toString())) {
            binding.inputCardPin.error =  "Enter your credit card pin/cvv"
            return
        }
        val intent = Intent(this@PlaceYourOrderActivity, SuccessOrderActivity::class.java)
        intent.putExtra("RestaurantModel", restaurantModel)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1000) {
            setResult(RESULT_OK)
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_CANCELED)
        finish()
    }


}