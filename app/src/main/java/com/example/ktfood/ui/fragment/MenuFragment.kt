package com.example.ktfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.MenuAdapter
import com.example.ktfood.databinding.FragmentMenuBinding


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    private lateinit var binding:FragmentMenuBinding
    private lateinit var adapterFood : MenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    private val foodName = listOf("Bun Dau Mam Tom", "Pho Ha No", "Mi Quang", "Bánh mì", "Bánh xèo", "Ram chiên")
    val foodPrice = listOf("$2", "$3", "$1", "$2", "$3", "$1")
    val foodImages = listOf(R.drawable.item_cate, R.drawable.pho, R.drawable.miquang, R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)


    private val filterMenuFoodName = mutableListOf<String>()
    private val filterMenuFoodPrice = mutableListOf<String>()
    private val filterMenuFoodImage = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)


//        val drinksName = listOf("Bánh mì", "Bánh xèo", "Ram chiên")
//        val drinksPrice = listOf("$2", "$3", "$1")
//        val drinksImages = listOf(R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)

//        adapterFood = MenuAdapter(filterMenuFoodName, filterMenuFoodPrice,filterMenuFoodImage, requireContext())
//        val adapterDrinks = MenuAdapter(ArrayList(drinksName), ArrayList(drinksPrice), ArrayList(drinksImages))

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterFood

//        binding.recyclerView2.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView2.adapter = adapterDrinks
        setupSearchView()
        showAllMenu()
        
        return binding.root
    }

    private fun showAllMenu() {
        filterMenuFoodName.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodImage.clear()

        filterMenuFoodName.addAll(foodName)
        filterMenuFoodPrice.addAll(foodPrice)
        filterMenuFoodImage.addAll(foodImages)

        adapterFood.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        binding.searchView2.setOnQueryTextListener(object  :SearchView.OnQueryTextListener{
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
        filterMenuFoodName.clear()
        filterMenuFoodPrice.clear()
        filterMenuFoodImage.clear()

        foodName.forEachIndexed{ index, fN ->
            if(fN.contains(query, ignoreCase = true)){
                filterMenuFoodName.add(fN)
                filterMenuFoodPrice.add(foodPrice[index])
                filterMenuFoodImage.add(foodImages[index])
            }
        }
        adapterFood.notifyDataSetChanged()
    }

    companion object {

    }
}