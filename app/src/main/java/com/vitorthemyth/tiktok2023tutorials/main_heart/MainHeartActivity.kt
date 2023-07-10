package com.vitorthemyth.tiktok2023tutorials.main_heart

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.TranslateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.vitorthemyth.tiktok2023tutorials.R
import com.vitorthemyth.tiktok2023tutorials.databinding.ActivityHeartBinding


class MainHeartActivity : AppCompatActivity() {


    private lateinit var leftBitmap: Bitmap
    private lateinit var rightBitmap: Bitmap

    private lateinit var binding: ActivityHeartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHeartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val drawable = ContextCompat.getDrawable(this, R.drawable.heart)
        val heartBitmap =
            Bitmap.createBitmap(
                drawable!!.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

        val canvas = Canvas(heartBitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        // Calculate the width and height of each half of the heart
        val halfWidth = heartBitmap.width / 2
        val height = heartBitmap.height

        // Create two new bitmaps for the left and right halves of the heart
        leftBitmap = Bitmap.createBitmap(heartBitmap, 0, 0, halfWidth, height)
        rightBitmap = Bitmap.createBitmap(heartBitmap, halfWidth, 0, halfWidth, height)

        // Set the left and right ImageViews to display the corresponding bitmaps
        binding.leftHeart.setImageBitmap(leftBitmap)
        binding.rightHeart.setImageBitmap(rightBitmap)

        initViews()
    }

    private fun initViews() {
        binding.mainHeart.setOnClickListener {
            // Hide the main heart ImageView
            hideEntireHeartAndDisplayPieces()

            // Create a new AnimatorSet to animate the left and right heart ImageViews
            val animatorSet = AnimatorSet()

            // Create the ObjectAnimator objects to move the left and right heart ImageViews
            val (leftAnimator, rightAnimator) = defineAnimationBehavior()

            // Add the ObjectAnimator objects to the AnimatorSet
            animatorSet.playTogether(leftAnimator, rightAnimator)

            // Add a listener to the AnimatorSet to show the main heart ImageView when the animation ends
            animationProgressListener(animatorSet)

            // Start the animation
            animatorSet.start()
        }
    }

    private fun animationProgressListener(animatorSet: AnimatorSet) {
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                val name = "Porque"
                binding.name.visibility = View.VISIBLE
                binding.name.text = name

                Handler(Looper.getMainLooper()).postDelayed({
                    val anim = TranslateAnimation(0f, 0f, 0f, 200f)
                    anim.duration = 1000
                    anim.fillAfter = true
                    binding.name.startAnimation(anim)
                    startAdjectives()
                }, 1000)
            }

            override fun onAnimationCancel(p0: Animator) {

            }

            override fun onAnimationRepeat(p0: Animator) {

            }
        })
    }

    private fun defineAnimationBehavior(): Pair<ObjectAnimator, ObjectAnimator> {
        val leftAnimator = ObjectAnimator.ofFloat(binding.leftHeart, "translationX", -200f)
        val rightAnimator = ObjectAnimator.ofFloat(binding.rightHeart, "translationX", 200f)

        // Set the duration and interpolator for the ObjectAnimator objects
        leftAnimator.duration = 1000
        leftAnimator.interpolator = AccelerateInterpolator()
        rightAnimator.duration = 1000
        rightAnimator.interpolator = AccelerateInterpolator()
        return Pair(leftAnimator, rightAnimator)
    }

    private fun hideEntireHeartAndDisplayPieces() {
        binding.mainHeart.visibility = View.GONE
        binding.leftHeart.visibility = View.VISIBLE
        binding.rightHeart.visibility = View.VISIBLE
    }

    private fun startAdjectives() {
        loadContainerBackground()

        val placeHolder = "Não é você quem: "
        val list = listOf<String>(
            "$placeHolder ESTUDOU",
            "$placeHolder FOI RESILIENTE",
            "$placeHolder APRENDEU OUTRO IDIOMA",
            "$placeHolder FAZ PARECEER FACIL"
        )

        var currentIndex = 0
        val updateTextRunnable = object : Runnable {

            override fun run() {
                binding.adjectives.text = list[currentIndex]
                binding.adjectives.alpha = 0f
                binding.adjectives.animate().alpha(1f).duration = 500
                currentIndex = (currentIndex + 1) % list.size
                binding.adjectives.postDelayed(this, 1500)
            }
        }
        binding.adjectives.visibility = View.VISIBLE
        binding.adjectives.post(updateTextRunnable)
    }


    private fun resetAllScreenStates() {
        resetAdjectivesAndNameState()
        resetHeartPiecesState()
        resetContainerState()
    }

    private fun resetAdjectivesAndNameState() {
        val fadeInAnimation = ValueAnimator.ofFloat(1f, 0f)
        fadeInAnimation.apply {
            duration = 2000 // 2 second
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.adjectives.alpha = alpha
                binding.name.alpha = alpha
            }
            start()
        }
    }

    private fun resetHeartPiecesState() {
        val fadeInAnimation = ValueAnimator.ofFloat(1f, 0f)
        fadeInAnimation.apply {
            duration = 2000 // 2 second
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.leftHeart.alpha = alpha
                binding.rightHeart.alpha = alpha
            }
            start()
        }
    }

    private fun resetContainerState() {
        val fadeInAnimation = ValueAnimator.ofFloat(1f, 0f)
        fadeInAnimation.apply {

            binding.container.setBackgroundColor(
                ContextCompat.getColor(
                    this@MainHeartActivity,
                    R.color.white
                )
            )
            duration = 2000 // 2 second
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.container.alpha = alpha

            }
            start()
        }
    }

    private fun loadContainerBackground() {
        val fadeInAnimation = ValueAnimator.ofFloat(0f, 1f)
        fadeInAnimation.apply {
            duration = 3000 // 1.5 second

            binding.container.setBackgroundResource(R.drawable.babydonthurtme)
            addUpdateListener { animation ->
                val alpha = animation.animatedValue as Float
                binding.container.alpha = alpha
            }
            start()
        }
    }
}




