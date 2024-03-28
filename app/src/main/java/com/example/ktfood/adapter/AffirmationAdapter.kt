package com.example.ktfood.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.R
import com.example.ktfood.model.Affirmation


class AffirmationAdapter(private val context: Context, private val dataset: List<Affirmation>) :
    RecyclerView.Adapter<AffirmationAdapter.AffirmationViewHolder>() {

    // ViewHolder đại diện cho mỗi item trong RecyclerView
    class AffirmationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    // Tạo ViewHolder mới khi không có ViewHolder cũ nào có sẵn để tái sử dụng
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffirmationViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return AffirmationViewHolder(adapterLayout)
    }

    // Gắn dữ liệu vào ViewHolder
    override fun onBindViewHolder(holder: AffirmationViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    // Trả về số lượng item trong dataset
    override fun getItemCount(): Int {
        return dataset.size
    }
}
