
package com.example.assignment.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.assignment.R
import com.example.assignment.view.utils.ErrorBaseActivity
import com.example.jet2travel.view.home.HomeActivity


class SplashActivity : ErrorBaseActivity() {

    //private val SPLASH_TIME_OUT = 4000L
    private val SPLASH_TIME_OUT = 1000L

    private var ivSplash: ImageView? = null
    private var tvWelcome : TextView? = null

    var topAnimantion: Animation? = null
    var slideInAnimation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initViews();
        init()
        setAnimation()
        setHandler()
    }

    private fun initViews (){
        ivSplash = findViewById(R.id.ivSplash)
        tvWelcome = findViewById(R.id.tvWelcome)
    }

    private fun init(){
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
    }

    private fun setAnimation(){
        ivSplash?.animation = slideInAnimation
        tvWelcome?.animation = topAnimantion
    }

    private fun setHandler(){
        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(R.anim.slide_in_frm_right, R.anim.slide_out_from_left)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
