package com.example.ktfood.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktfood.databinding.BuyAgainItemBinding

class BuyAgainAdapter (private val bAFoodName: MutableList<String>,
                       private val bAPriceName: MutableList<String>,
                       private val bAImageName: MutableList<String>,
                       private val rcontext: Context
    ) : RecyclerView.Adapter<BuyAgainAdapter.BuyAgainViewHolder>() {


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

    inner class BuyAgainViewHolder(private val binding : BuyAgainItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(s: String, s1: String, i: String) {
            binding.textView12.text = s
            binding.textView13.text = s1
            val uriString = i
            val uri = Uri.parse(uriString)
            Glide.with(rcontext).load(uri).into(binding.imageView4)

        }

    }
}