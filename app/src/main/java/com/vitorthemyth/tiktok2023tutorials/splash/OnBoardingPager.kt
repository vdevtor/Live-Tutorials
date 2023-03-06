package com.vitorthemyth.tiktok2023tutorials.splash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.vitorthemyth.tiktok2023tutorials.databinding.IntroContentBinding
import com.vitorthemyth.tiktok2023tutorials.splash.list.IntroTip

class OnBoardingPager(
    private val layoutInflater: LayoutInflater,
    private val tips : Array<IntroTip>
) : PagerAdapter(){

    private lateinit var binding: IntroContentBinding

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = IntroContentBinding.inflate(layoutInflater, container, false)
        binding.title.text = tips[position].title
        binding.subtitle.text = tips[position].subtitle
        binding.container.setBackgroundResource(tips[position].backgroundImage)

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount() = tips.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}