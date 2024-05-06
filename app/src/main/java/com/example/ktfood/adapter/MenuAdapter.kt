package com.example.ktfood.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.car.ui.toolbar.MenuItem.OnClickListener
import com.bumptech.glide.Glide
import com.example.ktfood.databinding.CartItemBinding
import com.example.ktfood.databinding.MenuItemBinding
import com.example.ktfood.model.MenuItem
import com.example.ktfood.ui.DetailsActivity

class MenuAdapter(
                  private val menuItems: List<MenuItem>,
                  private val requireContext: Context
    ) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {



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
        init{
            binding.root.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    openDetails(position)
                }

            }
        }
        private fun openDetails(position: Int) {
            val menuItem = menuItems[position]
//pass data
            val intent = Intent(requireContext, DetailsActivity::class.java).apply {
                putExtra("MenuItemName" ,menuItem.foodName)
                putExtra("MenuItemPrice" ,menuItem.foodPrice)
                putExtra("MenuItemImage" ,menuItem.foodImage)
                putExtra("MenuItemDescription" ,menuItem.foodDescription)
                putExtra("MenuItemIngredient" ,menuItem.foodIngredient)

            }

            //start dt activity
            requireContext.startActivity(intent)
        }
        fun bind(position: Int) {
            binding.apply {
                menuName.text = menuItems[position].foodName
                menuPrice.text = "$"+menuItems[position].foodPrice
                val uri = Uri.parse(menuItems[position].foodImage)
                Glide.with(requireContext).load(uri).into(imageMenuItem)
            }
        }
    }



    interface OnClickListener {
        fun onItemCLick(position: Int) {

        }
    }
}




