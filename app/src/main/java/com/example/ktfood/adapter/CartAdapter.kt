package com.example.ktfood.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ktfood.databinding.CartItemBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartAdapter (
            private val context :Context,
            private val items:MutableList<String>,
            private val price:MutableList<String>,
            private val images:MutableList<String>,
            private val description:MutableList<String>,
            private val cartQuantity:MutableList<Int>,
            private val ingredient : MutableList<String>
    )
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){


        private val auth = FirebaseAuth.getInstance()

    init{
        val database = FirebaseDatabase.getInstance()
        val userId = auth.currentUser?.uid?:""
        val cartItemNumber = items.size

        itemQuantities = IntArray(cartItemNumber){1}
        cartItemsReference = database.reference.child("user").child(userId).child("CartItem")
    }

    companion object{
        private var itemQuantities : IntArray = intArrayOf()
        private lateinit var cartItemsReference : DatabaseReference
    }



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
                val quantity = itemQuantities[position]
                namecart.text = items[position]
                pricecart.text = price[position]


                val uriString = images[position]
                val uri = Uri.parse(uriString)
/////////////////////////
                Glide.with(context).load(uri).into(imageView)
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
            if(itemQuantities[position]>1){
                itemQuantities[position]--
                binding.amount.text = itemQuantities[position].toString()
            }
        }
        private  fun inceaseQuatity(position: Int){
            if(itemQuantities[position]<10){
                itemQuantities[position]++
                binding.amount.text = itemQuantities[position].toString()
            }
        }
        private fun deleteItem(position: Int){
            val positionRetrieve = position
            getUniqueKeyAtPosition(positionRetrieve){
                    uniqueKey -> if(uniqueKey != null){
                        removeItem(position, uniqueKey)
                }
            }
        }

        private fun removeItem(position: Int, uniqueKey: String) {
            if(uniqueKey != null){
                cartItemsReference.child(uniqueKey).removeValue().addOnSuccessListener {
                    items.removeAt(position)
                    images.removeAt(position)
                    description.removeAt(position)
                    cartQuantity.removeAt(position)
                    price.removeAt(position)
                    ingredient.removeAt(position)
                    Toast.makeText(context, "Complete to delete", Toast.LENGTH_SHORT).show()

                    itemQuantities = itemQuantities.filterIndexed{ index, i -> index!= position }.toIntArray()
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                }.addOnFailureListener{
                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun getUniqueKeyAtPosition(positionRetrieve: Int, onComplete:(String?) ->Unit ) {
            cartItemsReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var uniqueKey:String? = null
                    snapshot.children.forEachIndexed { index, dataSnapshot ->
                        if(index == positionRetrieve){
                            uniqueKey = dataSnapshot.key
                            return@forEachIndexed
                        }
                    }
                    onComplete(uniqueKey)
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
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