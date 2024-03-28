package com.example.ktfood.ui.fragment

import RecyclerViewAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ktfood.R
import com.example.ktfood.adapter.AffirmationAdapter
import com.example.ktfood.adapter.News
import com.example.ktfood.adapter.ViewPagerAdapter
import com.example.ktfood.data.DataCate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager2: ViewPager2 = rootView.findViewById(R.id.View_Pager2)
        val images = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,
            R.drawable.banner4, R.drawable.banner5, R.drawable.banner6, R.drawable.banner7)

        viewPager2.adapter = ViewPagerAdapter(images)
        //

//        val recyclerView: RecyclerView = rootView.findViewById(R.id.RecyclerCate)
//        val layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.layoutManager = layoutManager
//
//        // Chuẩn bị dữ liệu cho RecyclerView
//        val imageIDs = listOf(
//            R.drawable.item_cate,
//            R.drawable.main_logo
//        )
//        val text1 = listOf(
//            "1111",
//            "2222"
//        )
//        val text2 = listOf(
//            "1111",
//            "2222"
//        )
//        val text3 = listOf(
//            "1111",
//            "2222"
//        )
//
//        // Tạo danh sách tin tức
//        val newsList = ArrayList<News>()
//        for (i in imageIDs.indices) {
//            newsList.add(News(imageIDs[i], text1[i], text2[i], text3[i]))
//        }
//
//        // Khởi tạo Adapter và gán cho RecyclerView
//        val adapter = RecyclerViewAdapter(newsList)
//        recyclerView.adapter = adapter

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        // Thiết lập LayoutManager cho RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Tạo Adapter và thiết lập cho RecyclerView
        val dataSource = DataCate()
        val adapter = AffirmationAdapter(requireContext(), dataSource.loadAffirmations())
        recyclerView.adapter = adapter
        return rootView
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}