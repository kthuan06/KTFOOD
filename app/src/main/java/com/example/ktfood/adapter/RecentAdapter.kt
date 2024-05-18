package com.example.ktfood.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktfood.databinding.RecentItemBinding
import com.example.ktfood.model.MenuItem
import com.example.ktfood.model.OrderDetails
import com.example.ktfood.ui.DetailsActivity

class RecentAdapter(private var context : Context,
                    private var  foodNameList : ArrayList<String>,
                    private var  foodImageList : ArrayList<String>,
                    private var  foodPriceList : ArrayList<String>,
                    private var  foodQuantityList : ArrayList<Int>
    ): RecyclerView.Adapter<RecentAdapter.RecentViewHolder>()  {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentAdapter.RecentViewHolder {
        val binding = RecentItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return RecentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
       holder.bind(position)
    }

    override fun getItemCount(): Int = foodNameList.size
   inner class RecentViewHolder(private val binding : RecentItemBinding ):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                menuName.text = foodNameList[position]
                menuPrice.text = "$" + foodPriceList[position]
                textView21.text = foodQuantityList[position].toString()
                val uriS = foodImageList[position]
                val uri = Uri.parse(uriS)
                Glide.with(context).load(uri).into(imageMenuItem)
            }
        }
//       init{
//           binding.root.setOnClickListener {
//               val position = adapterPosition
//               val ItemDetail : List<MenuItem>
//               val menuItem = ItemDetail[position]
//
//               if(position != RecyclerView.NO_POSITION){
//                   val intent = Intent(context, DetailsActivity::class.java).apply {
//                       putExtra("MenuItemName" ,menuItem.foodName)
//                       putExtra("MenuItemPrice" ,menuItem.foodPrice)
//                       putExtra("MenuItemImage" ,menuItem.foodImage)
//                       putExtra("MenuItemDescription" ,menuItem.foodDescription)
//                       putExtra("MenuItemIngredient" ,menuItem.foodIngredient)
//                   }
//               }
//           }
//
//       }
    }
}