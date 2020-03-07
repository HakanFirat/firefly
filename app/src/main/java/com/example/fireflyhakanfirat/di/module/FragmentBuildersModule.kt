package com.example.fireflyhakanfirat.di.module

import com.example.fireflyhakanfirat.ui.login.LoginFragment
import com.example.fireflyhakanfirat.ui.register.RegisterFragment
import com.example.fireflyhakanfirat.ui.splash.SplashFragment
import com.example.fireflyhakanfirat.ui.userdetail.UserDetailFragment
import com.example.fireflyhakanfirat.ui.userlist.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserDetailFragment(): UserDetailFragment

}