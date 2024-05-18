package com.example.ktfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.RecentAdapter
import com.example.ktfood.databinding.ActivityRecentOrderBinding
import com.example.ktfood.model.OrderDetails

class RecentOrderActivity : AppCompatActivity() {
    private val binding : ActivityRecentOrderBinding by lazy {
        ActivityRecentOrderBinding.inflate(layoutInflater)
    }

    private lateinit var FoodNames : ArrayList<String>
    private lateinit var FoodPrices : ArrayList<String>
    private lateinit var FoodImages : ArrayList<String>
    private lateinit var FoodQuantities : ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            finish()
        }
        val recentOrders = intent.getSerializableExtra("RecentBuyOrder") as ArrayList<OrderDetails>
        recentOrders?.let { orderDetails ->
            if(orderDetails.isNotEmpty()){
                val recentOrder = orderDetails[0]

                FoodNames = recentOrder.foodName as ArrayList<String>
                FoodPrices = recentOrder.foodPrice as ArrayList<String>
                FoodImages = recentOrder.foodImage as ArrayList<String>
                FoodQuantities = recentOrder.foodQuantity as ArrayList<Int>
            }
        }
        setAdapter()

    }

    private fun setAdapter() {
        val rv = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = RecentAdapter(this, FoodNames, FoodImages, FoodPrices, FoodQuantities)
        rv.adapter = adapter
    }
}