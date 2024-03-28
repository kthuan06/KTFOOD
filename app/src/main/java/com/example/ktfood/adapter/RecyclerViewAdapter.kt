import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.R
import com.example.ktfood.adapter.News
import com.google.android.material.imageview.ShapeableImageView

class RecyclerViewAdapter(private val newList: ArrayList<News>):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // Khai báo ViewHolder cho các item trong RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ShapeableImageView = itemView.findViewById(R.id.cate_image)
        val textView1: TextView = itemView.findViewById(R.id.text_cate1)
        val textView2: TextView = itemView.findViewById(R.id.text_cate2)
        val textView3: TextView = itemView.findViewById(R.id.text_cate3)
    }


    // Tạo ViewHolder khi RecyclerView cần
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cate, parent, false) // Thay R.layout.your_item_layout bằng layout của item trong RecyclerView
        return ViewHolder(view)
    }

    // Liên kết dữ liệu với ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newList[position] // Lấy dữ liệu từ danh sách tại vị trí position
        holder.imageView.setImageResource(item.image)
        holder.textView1.text = item.text1
        holder.textView2.text = item.text2
        holder.textView3.text = item.text3

    }



    // Trả về số lượng item trong danh sách
    override fun getItemCount(): Int {
        return newList.size
    }
}
