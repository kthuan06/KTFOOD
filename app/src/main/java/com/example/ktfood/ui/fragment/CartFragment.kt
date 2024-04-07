package com.example.ktfood.ui.fragment

import com.example.ktfood.ui.PayOutActivity // Đảm bảo đường dẫn package là chính xác

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktfood.R
import com.example.ktfood.adapter.CartAdapter
import com.example.ktfood.databinding.FragmentCartBinding
import kotlinx.coroutines.flow.combine


class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentCartBinding.inflate(inflater, container, false)
        binding.proceed.setOnClickListener {
            val intent = Intent(requireContext(), PayOutActivity::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodName = listOf("Bun Dau Mam Tom", "Pho Ha No", "Mi Quang", "Bánh mì", "Bánh xèo", "Ram chiên")
        val price = listOf("$2", "$3", "$1", "$2", "$3", "$1")
        val amount = listOf("1", "2", "3", "1", "2", "3")
        val imagesFood = listOf(R.drawable.item_cate, R.drawable.pho, R.drawable.miquang,
            R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)
        val adapter_Cart = CartAdapter(ArrayList(foodName), java.util.ArrayList(price), ArrayList(imagesFood))
        binding.cartlist.layoutManager = LinearLayoutManager(requireContext())
        binding.cartlist.adapter = adapter_Cart



    }
    companion object {

    }
}