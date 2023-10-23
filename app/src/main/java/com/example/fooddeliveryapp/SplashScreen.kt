package com.example.fooddeliveryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.fooddeliveryapp.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    lateinit var splashBinding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)


        val alphaAnimation = AnimationUtils.loadAnimation(applicationContext,R.anim.splash_anim)
        splashBinding.textViewSplash.startAnimation(alphaAnimation)

        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed(object : Runnable{
            override fun run() {
                val intent = Intent(this@SplashScreen,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)


    }
}