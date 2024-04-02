package com.example.ktfood.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.BuyAgainAdapter
import com.example.ktfood.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {
    private lateinit var binding : FragmentHistoryBinding
    private lateinit var bAAdapter : BuyAgainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView(){
        val bAFoodName = arrayListOf("Food1", "Food2" , "Food3", "Food2" , "Food3", "Food2" , "Food3", "Food2" , "Food3")
        val bAPriceName = arrayListOf("$1", "$2" , "$3", "$2" , "$3", "$2" , "$3", "$2" , "$3")
        val bAImageName = arrayListOf(R.drawable.banhmi, R.drawable.banhxeo, R.drawable.miquang,
            R.drawable.banhxeo, R.drawable.miquang, R.drawable.banhxeo,
            R.drawable.miquang, R.drawable.banhxeo, R.drawable.miquang)
        bAAdapter = BuyAgainAdapter(bAFoodName, bAPriceName, bAImageName)
        binding.listBuyAgain.layoutManager = LinearLayoutManager(requireContext())
        binding.listBuyAgain.adapter = bAAdapter
    }
    companion object {


    }
}