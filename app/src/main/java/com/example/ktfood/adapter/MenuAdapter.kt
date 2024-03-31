package com.example.ktfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.databinding.CartItemBinding
import com.example.ktfood.databinding.MenuItemBinding

class MenuAdapter(private val menuItems:MutableList<String>,
                  private val menuPrices:MutableList<String>,
                  private val menuImages:MutableList<Int>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuAdapter.MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItems.size

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                menuName.text = menuItems[position]
                menuPrice.text = menuPrices[position]
                imageMenuItem.setImageResource(menuImages[position])
            }
        }


    }
}