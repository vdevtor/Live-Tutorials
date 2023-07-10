package com.vitorthemyth.tiktok2023tutorials.commum_errors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vitorthemyth.tiktok2023tutorials.R

class CommonMistakesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commun_mistakesctivity)
        initArray()
    }


    private fun initArray(){
        val list = listOf<Int>(1,2,3,4,5,6)

        println(message = list[6])
    }
}