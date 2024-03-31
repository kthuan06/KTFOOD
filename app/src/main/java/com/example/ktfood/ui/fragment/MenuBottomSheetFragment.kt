package com.example.ktfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.MenuAdapter
import com.example.ktfood.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [MenuBottomSheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentMenuBottomSheetBinding
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
        val foodName = listOf("Bun Dau Mam Tom", "Pho Ha No", "Mi Quang")
        val foodPrice = listOf("$2", "$3", "$1")
        val foodImages = listOf(R.drawable.item_cate, R.drawable.pho, R.drawable.miquang)

        val drinksName = listOf("Bánh mì", "Bánh xèo", "Ram chiên")
        val drinksPrice = listOf("$2", "$3", "$1")
        val drinksImages = listOf(R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)

        val adapterFood = MenuAdapter(ArrayList(foodName), ArrayList(foodPrice), ArrayList(foodImages))

        val adapterDrinks = MenuAdapter(ArrayList(drinksName), ArrayList(drinksPrice), ArrayList(drinksImages))

        binding.foodList.layoutManager = LinearLayoutManager(requireContext())
        binding.foodList.adapter = adapterFood

        binding.drinksList.layoutManager = LinearLayoutManager(requireContext())
        binding.drinksList.adapter = adapterDrinks

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
    companion object {

            }

}