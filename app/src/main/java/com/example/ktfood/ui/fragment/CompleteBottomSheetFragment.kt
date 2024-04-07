package com.example.ktfood.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ktfood.R
import com.example.ktfood.databinding.FragmentCompleteBottomSheetBinding
import com.example.ktfood.ui.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CompleteBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentCompleteBottomSheetBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompleteBottomSheetBinding.inflate(layoutInflater, container, false)
        binding.backhome.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}