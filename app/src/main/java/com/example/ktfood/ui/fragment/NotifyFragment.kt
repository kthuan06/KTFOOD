package com.example.ktfood.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ktfood.databinding.FragmentNotifyBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotifyFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentNotifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentNotifyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {

    }
}