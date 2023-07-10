package com.vitorthemyth.tiktok2023tutorials.splash.list

import androidx.annotation.DrawableRes
import com.vitorthemyth.tiktok2023tutorials.R

data class IntroTip(
    val title: String,
    val subtitle: String,
    @DrawableRes val backgroundImage: Int
)


fun provideIntroTipsList(): Array<IntroTip> {
    return arrayOf(
        IntroTip(
            title = "Keep Coding",
            subtitle = "training every day means a lot on this process \n so keep going never give up",
            backgroundImage = R.color.egg_plant
        ),
        IntroTip(
            title = "Learn from Mistakes",
            subtitle = "failure leads to acknowledgment, don't be afraid to fail \n be afraid of not trying",
            backgroundImage = R.color.heather
        ),
        IntroTip(
            title = "Every Step Counts",
            subtitle = "Don't forget to evaluate small wins",
            backgroundImage = R.color.iris
        ),
    )
}
