package com.vitorthemyth.tiktok2023tutorials.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.vitorthemyth.tiktok2023tutorials.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallBack)
    }



    private val onBackPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

        }
    }
}