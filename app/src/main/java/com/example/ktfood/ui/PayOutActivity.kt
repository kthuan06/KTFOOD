package com.example.ktfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ktfood.R
import com.example.ktfood.databinding.ActivityPayOutBinding
import com.example.ktfood.ui.fragment.CompleteBottomSheetFragment

class PayOutActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var binding : ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPayOutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.confirm.setOnClickListener {
            val bottomSheet = CompleteBottomSheetFragment()
            bottomSheet.show(supportFragmentManager, "test")
        }
//        var navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
//        navController = navHostFragment.navController
//        binding.backOrder.setOnClickListener{
//            navController.navigate(R.id.cartFragment)
//        }
    }
}
