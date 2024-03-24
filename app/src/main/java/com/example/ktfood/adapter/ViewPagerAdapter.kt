package com.example.ktfood.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.R

class ViewPagerAdapter(private val img: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_Banner)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_banner, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val actualPosition = position % img.size // Lấy vị trí thực sự của ảnh trong danh sách
        holder.imageView.setImageResource(img[actualPosition])
    }

    override fun getItemCount(): Int {
        // Đảm bảo rằng số lượng item không bao giờ là 0
        return if (img.isNotEmpty()) Int.MAX_VALUE else 0
    }
}
