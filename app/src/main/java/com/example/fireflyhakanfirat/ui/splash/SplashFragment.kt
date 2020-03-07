package com.example.fireflyhakanfirat.ui.splash

import android.os.Handler
import android.view.View
import com.example.fireflyhakanfirat.R
import com.example.fireflyhakanfirat.ui.BaseFragment

class SplashFragment: BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_splash

    override fun initViewsOnViewCreated(view: View) {
        super.initViewsOnViewCreated(view)
        setToolbarVisibility(false)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            navigateTo(R.id.action_fragmentSplash_to_fragmentLogin)
        },3000)
    }
}