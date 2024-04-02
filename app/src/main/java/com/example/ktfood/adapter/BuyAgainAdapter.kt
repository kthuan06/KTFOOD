package com.example.ktfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.databinding.BuyAgainItemBinding

class BuyAgainAdapter (private val bAFoodName: ArrayList<String>,
                       private val bAPriceName: ArrayList<String>,
                       private val bAImageName: ArrayList<Int>) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuyAgainAdapter.BuyAgainViewHolder {
        val binding = BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuyAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAgainAdapter.BuyAgainViewHolder, position: Int) {
        holder.bind(bAFoodName[position], bAPriceName[position], bAImageName[position])
    }

    override fun getItemCount(): Int = bAFoodName.size

    class BuyAgainViewHolder(private val binding : BuyAgainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String, s1: String, i: Int) {
            binding.textView12.text = s
            binding.textView13.text = s1
            binding.imageView4.setImageResource(i)
        }

    }
}