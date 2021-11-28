package com.marvel.challenge.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.marvel.challenge.R
import com.marvel.challenge.presentation.common.BaseActivity
import com.marvel.challenge.presentation.navigator.Navigator
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashView {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        initTimerToStart()
    }

    override fun getActivityLayout() = R.layout.activity_splash

    private fun initTimerToStart() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToList()
        }, 1000)
    }


    override fun onResume() {
        super.onResume()
    }


    override fun goToList() {
        navigator.navigateToListCharacters(this)
        finish()
    }

    override fun showLoading() {
        // Nothing to do
    }

    override fun hideLoading() {
        // Nothing to do
    }

}