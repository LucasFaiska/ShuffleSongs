package com.lfaiska.shufflesongs.presentation.scenes.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lfaiska.shufflesongs.R
import com.lfaiska.shufflesongs.presentation.scenes.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed( { navigateToHome() }, TIME_TO_SHOW_SPLASH )
    }

    private fun navigateToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    companion object {
        const val TIME_TO_SHOW_SPLASH = 2000L
    }
}
