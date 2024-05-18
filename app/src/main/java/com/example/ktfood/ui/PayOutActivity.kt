package com.example.ktfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ktfood.R
import com.example.ktfood.databinding.ActivityPayOutBinding
import com.example.ktfood.model.OrderDetails
import com.example.ktfood.ui.fragment.CompleteBottomSheetFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PayOutActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var binding : ActivityPayOutBinding

    private lateinit var auth : FirebaseAuth
    private lateinit var name : String
    private lateinit var address : String
    private lateinit var phone : String
    private lateinit var totalAmount : String
    private lateinit var foodItemName : ArrayList<String>
    private lateinit var foodItemPrice : ArrayList<String>
    private lateinit var foodItemImage : ArrayList<String>
    private lateinit var foodItemDescription : ArrayList<String>
    private lateinit var foodItemIngredient : ArrayList<String>
    private lateinit var foodItemQuantities : ArrayList<Int>
    private lateinit var dataBaseReference : DatabaseReference
    private lateinit var userID : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //init
        auth = FirebaseAuth.getInstance()
        dataBaseReference = FirebaseDatabase.getInstance().reference

        //set user data
        setUserData()
        //get user data from firebase
        val intent = intent
        foodItemName = intent.getStringArrayListExtra("FoodItemName") as ArrayList<String>
        foodItemPrice = intent.getStringArrayListExtra("FoodItemPrice") as ArrayList<String>
        foodItemImage = intent.getStringArrayListExtra("FoodItemImage") as ArrayList<String>
        foodItemDescription = intent.getStringArrayListExtra("FoodItemDescription") as ArrayList<String>
        foodItemIngredient = intent.getStringArrayListExtra("FoodItemIngredient") as ArrayList<String>
        foodItemQuantities = intent.getIntegerArrayListExtra("FoodItemQuantities") as ArrayList<Int>

        totalAmount = "$" + caculateTotalAmount().toString()

        binding.pricePay.isEnabled = false
        binding.pricePay.setText(totalAmount)

        binding.backOrder.setOnClickListener{
            finish()
        }

        binding.confirm.setOnClickListener {
            name = binding.editName.text.toString().trim()
            address = binding.editAddress.text.toString().trim()
            phone = binding.editPhone.text.toString().trim()

            if(name.isBlank() || address.isBlank() || phone.isBlank()){
                Toast.makeText(this, "Fill all", Toast.LENGTH_SHORT).show()
            }else{
                placeOrder()
            }


        }
    }

    private fun placeOrder() {
        userID = auth.currentUser?.uid?:""
        val time = System.currentTimeMillis()
        val itemPushKey = dataBaseReference.child("OrderDetails").push().key
        val orderDetails = OrderDetails(
            userID,
            name,
            foodItemName,
            foodItemPrice,
            foodItemImage,
            foodItemQuantities,
            address,
            totalAmount,
            phone,
            time,
            itemPushKey,
            false,
            false
            )
        val orderReference = dataBaseReference.child("OrderDetails").child(itemPushKey!!)
        orderReference.setValue(orderDetails).addOnSuccessListener {
            val bottomSheet = CompleteBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "test")
            removeItemCart()
            addOrderToHistory(orderDetails)
        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to order", Toast.LENGTH_SHORT).show()
            }

    }

    private fun addOrderToHistory(orderDetails: OrderDetails){
        dataBaseReference.child("user").child(userID).child("History")
            .child(orderDetails.itemPushKey!!)
            .setValue(orderDetails).addOnCompleteListener {

            }
    }

    private fun removeItemCart() {
        val cartItemReference = dataBaseReference.child("user").child(userID).child("CartItem")
        cartItemReference.removeValue()
    }

    private fun caculateTotalAmount(): Int {
        var totalAmount = 0
        for (i in 0 until foodItemPrice.size){
            var price = foodItemPrice[i]
            val lastChar = price.last()
            val priceIntValue = if (lastChar == '$'){
                price.dropLast(1).toInt()
            }else{
                price.toInt()
            }
            var quantity = foodItemQuantities[i]
            totalAmount+=priceIntValue*quantity
        }
        return totalAmount
    }

    private fun setUserData() {
        val user = auth.currentUser
        if(user!=null){
            val userId = user.uid
            val userReference = dataBaseReference.child("user").child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val names = snapshot.child("name").getValue(String::class.java)?:""
                        val addresses   = snapshot.child("address").getValue(String::class.java)?:""
                        val phones = snapshot.child("phone").getValue(String::class.java)?:""
                        binding.apply {
                            editName.setText(names)
                            editAddress.setText(addresses)
                            editPhone.setText(phones)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}
