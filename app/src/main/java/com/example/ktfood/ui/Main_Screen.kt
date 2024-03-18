package com.example.ktfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.ktfood.R

class Main_Screen : AppCompatActivity() {

    private lateinit var main_logo: ImageView

//    override fun onStart() {
//        super.onStart()
//        window.statusBarColor = resources.getColor(R.color.bg_main)
//        window.navigationBarColor = resources.getColor(R.color.bg_main)
//    }
    override fun onStart() {
        super.onStart()

        // Màu sắc bạn muốn thiết lập (sử dụng màu từ resources)
        val bgColor = ContextCompat.getColor(this, R.color.bg_main)

        window.statusBarColor = bgColor
        window.navigationBarColor = bgColor
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        main_logo = findViewById(R.id.main_logo)

        main_logo.startAnimation(loadAnimation(this, R.anim.out_anim))

        Handler().postDelayed({
            val intent = Intent(this,  MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1200)
    }
}