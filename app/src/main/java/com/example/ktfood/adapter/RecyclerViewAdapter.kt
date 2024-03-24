import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView
import com.example.ktfood.R

class RecyclerViewAdapter(private val context: Context, private val data: List<Int>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // Khai báo ViewHolder cho các item trong RecyclerView
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val const: ConstraintLayout? = itemView.findViewById<ConstraintLayout>(R.id.constraint_cate)
    }


    // Tạo ViewHolder khi RecyclerView cần
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false) // Thay R.layout.your_item_layout bằng layout của item trong RecyclerView
        return ViewHolder(view)
    }

    // Liên kết dữ liệu với ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    // Trả về số lượng item trong danh sách
    override fun getItemCount(): Int {
        return data.size
    }
}
