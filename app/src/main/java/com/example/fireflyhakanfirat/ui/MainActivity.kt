package com.example.fireflyhakanfirat.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.fireflyhakanfirat.App
import com.example.fireflyhakanfirat.R
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var app: App

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //showFragment()

        val host = NavHostFragment.create(R.navigation.nav_host)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, host).setPrimaryNavigationFragment(host).commit()
    }

}
