package com.example.ktfood.ui.fragment

import com.example.ktfood.ui.PayOutActivity // Đảm bảo đường dẫn package là chính xác

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.CartAdapter
import com.example.ktfood.databinding.FragmentCartBinding
import com.example.ktfood.model.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.combine


class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var foodNames:MutableList<String>
    private lateinit var foodPrices:MutableList<String>
    private lateinit var foodImageUri:MutableList<String>
    private lateinit var foodDescriptions:MutableList<String>
    private lateinit var foodIngredients:MutableList<String>
    private lateinit var quantity:MutableList<Int>
    private lateinit var cartAdapter:CartAdapter
    private lateinit var userID : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        //init
        auth = FirebaseAuth.getInstance()

        retrireveCartItems()



        binding.proceed.setOnClickListener {
            getOrderItemsDetails()



        }


        return binding.root
    }

    private fun getOrderItemsDetails() {
        val orderIdReference: DatabaseReference= database.reference.child("user").child(userID).child("CartItem")

        val foodName = mutableListOf<String>()
        val foodPrice = mutableListOf<String>()
        val foodImage = mutableListOf<String>()
        val foodDescription = mutableListOf<String>()
        val foodIngredient = mutableListOf<String>()
        val foodQuantities = cartAdapter.getUpdateQuantities()

        orderIdReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSS in snapshot.children){
                    val orderItem = foodSS.getValue(CartItem::class.java)

                    orderItem?.foodName?.let { foodName.add(it) }
                    orderItem?.foodPrice?.let { foodPrice.add(it) }
                    orderItem?.foodImage?.let { foodImage.add(it) }
                    orderItem?.foodDescription?.let { foodDescription.add(it) }
                    orderItem?.foodIngredient?.let { foodIngredient.add(it) }
                }
                orderNow(foodName, foodPrice, foodImage, foodDescription, foodIngredient, foodQuantities)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Order failed", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun orderNow(foodName: MutableList<String>,
                         foodPrice: MutableList<String>,
                         foodImage: MutableList<String>,
                         foodDescription: MutableList<String>,
                         foodIngredient: MutableList<String>,
                         foodQuantities: MutableList<Int>) {
    if(isAdded && context!=null){
        val intent = Intent(requireContext(), PayOutActivity::class.java)
        intent.putExtra("FoodItemName" ,foodName as ArrayList<String>)
        intent.putExtra("FoodItemPrice" ,foodPrice as ArrayList<String>)
        intent.putExtra("FoodItemImage" ,foodImage as ArrayList<String>)
        intent.putExtra("FoodItemDescription" ,foodDescription as ArrayList<String>)
        intent.putExtra("FoodItemIngredient" ,foodIngredient as ArrayList<String>)
        intent.putExtra("FoodItemQuantities" ,foodQuantities as ArrayList<Int>)
        startActivity(intent)
    }

    }

    private fun retrireveCartItems() {
        database = FirebaseDatabase.getInstance()
        userID = auth.currentUser?.uid?:""
        val foodReference : DatabaseReference = database.reference.child("user").child(userID).child("CartItem")

        foodNames = mutableListOf()
        foodPrices = mutableListOf()
        foodDescriptions = mutableListOf()
        foodIngredients = mutableListOf()
        foodImageUri = mutableListOf()
        quantity = mutableListOf()

        foodReference.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSS in snapshot.children){
                    val cartItems = foodSS.getValue(CartItem::class.java)

                    cartItems?.foodName?.let { foodNames.add(it) }
                    cartItems?.foodPrice?.let { foodPrices.add(it) }
                    cartItems?.foodDescription?.let { foodDescriptions.add(it) }
                    cartItems?.foodImage?.let { foodImageUri.add(it) }
                    cartItems?.foodQuantity?.let { quantity.add(it) }
                    cartItems?.foodIngredient?.let { foodIngredients.add(it) }
                }
                setAdapter()
            }

            private fun setAdapter() {
                cartAdapter = CartAdapter(requireContext(), foodNames, foodPrices, foodImageUri, foodDescriptions, quantity, foodIngredients)
                binding.cartlist.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.cartlist.adapter = cartAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "data not fecth", Toast.LENGTH_SHORT).show()
            }
        })

    }


    companion object {

    }
}