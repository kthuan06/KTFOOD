package com.example.ktfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.databinding.CartItemBinding

class CartAdapter (private val items:MutableList<String>,
                   private val price:MutableList<String>,
                   private val images:MutableList<Int>)
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

private val quantities = IntArray(items.size){1}
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
//        val item = items[position]
//        val price = price[position]
//        val amount = amount[position]
//        val image = images[position]
//        holder.bind(item, price, amount, image)
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    //dung inner class de su dung dc cac bien da khai bao ow tren
    inner class CartViewHolder(private val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = quantities[position]
                namecart.text = items[position]
                pricecart.text = price[position]
                imageView.setImageResource(images[position])
                amount.text = quantity.toString()

                minusbutton.setOnClickListener {
                    deceaseQuatity(position)
                }

                plusbutton.setOnClickListener {
                    inceaseQuatity(position)
                }

                deletebutton.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(position)
                    }

                }


            }
        }
       private fun deceaseQuatity(position: Int){
            if(quantities[position]>1){
                quantities[position]--
                binding.amount.text = quantities[position].toString()
            }
        }
        private  fun inceaseQuatity(position: Int){
            if(quantities[position]<10){
                quantities[position]++
                binding.amount.text = quantities[position].toString()
            }
        }
        private fun deleteItem(position: Int){
            items.removeAt(position)
            price.removeAt(position)
            images.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }

        private val imageView = binding.imageView
//        fun bind(item: String, price: String , amount: String, image: Int) {
//        binding.namecart.text = item
//            binding.pricecart.text = price
//            binding.amount.text = amount
//            imageView.setImageResource(image)
//        }

    }
}