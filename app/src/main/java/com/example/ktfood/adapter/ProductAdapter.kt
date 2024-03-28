package com.example.ktfood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.databinding.ProductItemBinding

class ProductAdapter (private val items:List<String>, private val price:List<String>, private val image:List<Int>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
      val item = items[position]
        val price = price[position]
        val images = image[position]
    holder.bind(item, price, images)
    }

    override fun getItemCount(): Int {
       return items.size
    }
    class ProductViewHolder (private val binding:ProductItemBinding)
        :RecyclerView.ViewHolder(binding.root){
            private val imageView = binding.imageView2
        fun bind(item: String, price: String, images: Int) {
            binding.productName.text = item
            binding.productPrice.text = price
            imageView.setImageResource(images)
        }

    }

}