package com.example.ktfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.ktfood.R

class RegisterActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()

        // Màu sắc bạn muốn thiết lập (sử dụng màu từ resources)
        val bgColor = ContextCompat.getColor(this, R.color.bg_main)

        window.statusBarColor = bgColor
        window.navigationBarColor = bgColor
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
    }
}