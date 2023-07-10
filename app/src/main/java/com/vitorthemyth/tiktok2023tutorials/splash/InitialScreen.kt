package com.vitorthemyth.tiktok2023tutorials.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.orhanobut.hawk.Hawk
import com.vitorthemyth.tiktok2023tutorials.R
import com.vitorthemyth.tiktok2023tutorials.main.MainActivity
import com.vitorthemyth.tiktok2023tutorials.splash.list.provideIntroTipsList


/**
 * BATMAN MEANS THAT I'vE ALREADY PASSED THE ONBOARDING
 * AND NOW IM READY TO GO STRAIGHT TO THE MAIN ACTIVITY
 */
class InitialScreen : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {

            this.setKeepOnScreenCondition {
                viewModel.isLoading.value
            }

            this.setOnExitAnimationListener { splashScreenView ->
                if ( Hawk.get<Boolean>("BATMAN",false)){
                    finishAffinity()
                    startActivity(Intent(this@InitialScreen, MainActivity::class.java))
                } else{
                    splashScreenView.remove()
                }

            }
        }
        setContentView(R.layout.activity_onboarding)




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Use WindowInsetsController to set the system UI visibility
            window.insetsController?.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        } else {
            // Use deprecated SYSTEM_UI_FLAG_FULLSCREEN flag
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        val nextBtn = findViewById<Button>(R.id.next)
        val viewPager = findViewById<ViewPager>(R.id.view_Pager)
        val tips = provideIntroTipsList()
        viewPager.apply {
            adapter = OnBoardingPager(layoutInflater, tips = tips)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    addDots(tips.size, position)
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })

            setPageTransformer(true) { page, position ->
                page.alpha = 1 - kotlin.math.abs(position)
                page.translationX = -position * page.width

            }

            nextBtn.setOnClickListener {
                if (currentItem == size) {
                    Hawk.put("BATMAN",true)
                    finishAffinity()
                    startActivity(Intent(this@InitialScreen, MainActivity::class.java))
                } else {
                    setCurrentItem(currentItem + 1, true)
                }
            }
        }

        //dots
        addDots(tips.size)

    }

    private fun addDots(size: Int, position: Int = 0) {
        val dots = findViewById<LinearLayout>(R.id.dots)
        dots.removeAllViews()

        Array(size) {
            val textView = TextView(baseContext).apply {
                text = getString(R.string.dotted)
                textSize = 45f
                if (position == it) setTextColor(
                    ContextCompat.getColor(
                        this@InitialScreen,
                        R.color.yellow
                    )
                )
                else setTextColor(ContextCompat.getColor(this@InitialScreen, R.color.white))
            }
            dots.addView(textView)
        }
    }
}