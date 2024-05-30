package com.example.ktfood.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ktfood.R
import com.example.ktfood.adapter.BuyAgainAdapter
import com.example.ktfood.databinding.FragmentHistoryBinding
import com.example.ktfood.model.OrderDetails
import com.example.ktfood.ui.RecentOrderActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var bAAdapter : BuyAgainAdapter

    private lateinit var auth : FirebaseAuth
    private lateinit var dataBase : FirebaseDatabase
    private lateinit var userID : String
    private var listOfOrder : ArrayList<OrderDetails> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)

        //init auth
        auth = FirebaseAuth.getInstance()
        dataBase = FirebaseDatabase.getInstance()

        //Retrieve
        retrieveHistory()

        binding.recentbuyitem.setOnClickListener {
            seeItemRecentBuy()
        }

        binding.recievedButton.setOnClickListener {
            updateOrderStt()
        }


        return binding.root
    }

    private fun updateOrderStt() {
        val itemPushKey = listOfOrder[0].itemPushKey
        val completeOrder = dataBase.reference.child("CompleteOrder").child(itemPushKey!!)
        completeOrder.child("paymentRecieved").setValue(true)
        Toast.makeText(context, "Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun seeItemRecentBuy() {
        listOfOrder.firstOrNull()?.let { recentBuy ->
            val intent = Intent(requireContext(), RecentOrderActivity::class.java)
            intent.putExtra("RecentBuyOrder", listOfOrder)
            startActivity(intent)
        }
    }

    private fun retrieveHistory() {
        binding.recentbuyitem.visibility = View.VISIBLE
        userID = auth.currentUser?.uid?:""

        val buyItemReference = dataBase.reference.child("user").child(userID).child("History")
        val shortingQuery = buyItemReference.orderByChild("currentTime")

        shortingQuery.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(buySS in snapshot.children){
                    val buyItemHistory = buySS.getValue(OrderDetails::class.java)
                    buyItemHistory?.let {
                        listOfOrder.add(it)
                    }
                }
                listOfOrder.reverse()
                if(listOfOrder.isNotEmpty()){
                    setDataInRecentBuyItem()
                    setPreviousBuyItemRecyclerView()
                }
            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun setDataInRecentBuyItem() {
        binding.recentbuyitem.visibility = View.VISIBLE
        val recentOrderItem = listOfOrder.firstOrNull()
        recentOrderItem?.let {
            with(binding){
                textView12.text = it.foodName?.firstOrNull()?:""
                textView13.text = "$" + it.foodPrice?.firstOrNull()?:""
                val image = it.foodImage?.firstOrNull()?:""
                val uri = Uri.parse(image)
                Glide.with(requireContext()).load(uri).into(imageView4)

                val isOrderIsAccept = listOfOrder[0].orderAccepted
                if(isOrderIsAccept){
                    cardView4.background.setTint(Color.rgb(5,175,11))
                    recievedButton.visibility = View.VISIBLE
                }
//

            }
        }
    }


    private fun setPreviousBuyItemRecyclerView() {
        val bAFoodName = mutableListOf<String>()
        val bAPriceName = mutableListOf<String>()
        val bAImageName = mutableListOf<String>()
        for(i in 1 until listOfOrder.size){
            listOfOrder[i].foodName?.firstOrNull()?.let { bAFoodName.add(it)
                listOfOrder[i].foodPrice?.firstOrNull()?.let { bAPriceName.add(it)
                    listOfOrder[i].foodImage?.firstOrNull()?.let { bAImageName.add(it)
                    }
                }
                val rv = binding.listBuyAgain
                rv.layoutManager = LinearLayoutManager(requireContext())
                bAAdapter = BuyAgainAdapter(
                    bAFoodName,
                    bAPriceName,
                    bAImageName,
                    requireContext()
                )
                rv.adapter = bAAdapter
            }
        }
    }
}