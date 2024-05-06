package com.example.ktfood.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ktfood.R
import com.example.ktfood.databinding.ActivityDetailsBinding
import com.example.ktfood.model.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailsBinding
    private var foodName:String?=null
    private var foodPrice:String?=null
    private var foodImage:String?=null
    private var foodDescription:String?=null
    private var foodIngredient:String?=null

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //init authdatabase
        auth = FirebaseAuth.getInstance()

        foodName = intent.getStringExtra("MenuItemName")
        foodIngredient = intent.getStringExtra("MenuItemIngredient")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodPrice = intent.getStringExtra("MenuItemPrice")
        foodImage = intent.getStringExtra("MenuItemImage")

        with(binding){
            dtfoodName.text = foodName
            textView18.text = foodDescription
            textView20.text = foodIngredient

            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(imageView7)

        }

        binding.backdetails.setOnClickListener {
            finish()
        }

        binding.button3.setOnClickListener {
            addItemToCart()
        }

    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userID = auth.currentUser?.uid?:""


        val cartItem = CartItem(foodName.toString(), foodPrice.toString(),foodDescription.toString(), foodImage.toString(), 1 )

        //save data

        database.child("user").child(userID).child("CartItem").push().setValue(cartItem).addOnSuccessListener {
            Toast.makeText(this, "Items add cart successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Add cart failed", Toast.LENGTH_SHORT).show()
        }
    }
}