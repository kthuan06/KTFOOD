package com.example.ktfood.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.TestLooperManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ktfood.R
import com.example.ktfood.adapter.ProductAdapter
import com.example.ktfood.adapter.ViewPagerAdapter
import com.example.ktfood.data.DataCate
import com.example.ktfood.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.menuButton.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "test")
        }
        return binding.root

//        val viewPager2: ViewPager2 = rootView.findViewById(R.id.View_Pager2)
//        val images = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,
//            R.drawable.banner4, R.drawable.banner5, R.drawable.banner6, R.drawable.banner7)
//


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner4, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner5, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner6, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner7, ScaleTypes.FIT))
        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)


        val foodName = listOf("Bun Hue", "Pho Ha No", "Mi Quang", "Bánh mì", "Bánh xèo", "Ram chiên")
        val price = listOf("$2", "$3", "$1", "$2", "$3", "$1")
        val imagesFood = listOf(R.drawable.item_cate, R.drawable.pho, R.drawable.miquang,
            R.drawable.banhmi, R.drawable.banhxeo, R.drawable.ramchien)
        val adapter_Product = ProductAdapter(foodName, price, imagesFood)
        binding.productlist.layoutManager = LinearLayoutManager(requireContext())
        binding.productlist.adapter = adapter_Product
    }


    companion object {

    }
}