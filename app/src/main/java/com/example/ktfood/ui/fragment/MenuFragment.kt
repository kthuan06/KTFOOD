package com.example.ktfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.adapter.MenuAdapter
import com.example.ktfood.databinding.FragmentMenuBinding
import com.example.ktfood.model.MenuItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private lateinit var binding:FragmentMenuBinding
    private lateinit var adapter : MenuAdapter
    private lateinit var menuItems : MutableList<MenuItem>
    private lateinit var database : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        retriveMenuItems()
        setupSearchView()




        return binding.root
    }

    private fun retriveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val FoodRef : DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()

        FoodRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(FoodSS in snapshot.children){
                    val menuItem = FoodSS.getValue(MenuItem::class.java)
                    menuItem?.let{
                        menuItems.add(it)
                    }
                }
                showAllMenu()

            }


            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    private fun showAllMenu() {
        val filteredMenuItem = ArrayList(menuItems)
        setAdapter(filteredMenuItem)

    }

    private fun setAdapter(filteredMenuItem: List<MenuItem>) {
         adapter = MenuAdapter(filteredMenuItem, requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }



    private fun setupSearchView() {
        binding.searchView2.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText)
                return true
            }
        })
    }


    private fun filterMenuItems(query: String) {
        val filterMenuItem = menuItems.filter {
            it.foodName?.contains(query, ignoreCase = true)==true
        }

        setAdapter(filterMenuItem)
    }

    companion object {

    }
}