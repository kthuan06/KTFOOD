package com.example.ktfood.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.MenuAdapter
import com.example.ktfood.databinding.FragmentMenuBottomSheetBinding
import com.example.ktfood.model.MenuItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


/**
 * A simple [Fragment] subclass.
 * Use the [MenuBottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding
    private lateinit var database:FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItem>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.backMenu.setOnClickListener {
            dismiss()
        }

        retriveMenuItems()



        return binding.root
    }

    private fun retriveMenuItems() {
       database = FirebaseDatabase.getInstance()
        val foodRe: DatabaseReference = database.reference.child("menu")

        menuItems = mutableListOf()
        foodRe.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodss in snapshot.children){
                    val menuItem =  foodss.getValue(MenuItem::class.java)
                    // if menuItem not null then  menuItems.add(it)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                Log.d("Item", "onDataChange: data received")
                
                setAdapter()
            }



            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
    private fun setAdapter() {
        if(menuItems.isNotEmpty()){
            val adapter = MenuAdapter(menuItems, requireContext())

            binding.foodList.layoutManager = LinearLayoutManager(requireContext())
            binding.foodList.adapter = adapter

            binding.drinksList.layoutManager = LinearLayoutManager(requireContext())
            binding.drinksList.adapter = adapter
            Log.d("Item", "setAdapter: data set" )
        }else{
            Log.d("Item", "setAdapter: data not set" )
        }


    }

    companion object {

            }

}//        val foodName = listOf("Bun Dau Mam Tom", "Pho Ha No", "Mi Quang")
//        val foodPrice = listOf("$2", "$3", "$1")
//        val foodImages = listOf(R.drawable.item_cate, R.drawable.pho, R.drawable.miquang)
//
//        val drinksName = listOf("Bánh mì", "Bánh xèo", "Ram chiên")
//        val drinksPrice = listOf("$2", "$3", "$1")
//        val drinksImages = listOf(R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)
//
//        val adapterFood = MenuAdapter(ArrayList(foodName), ArrayList(foodPrice), ArrayList(foodImages), requireContext())
//
//        val adapterDrinks = MenuAdapter(ArrayList(drinksName), ArrayList(drinksPrice), ArrayList(drinksImages), requireContext())