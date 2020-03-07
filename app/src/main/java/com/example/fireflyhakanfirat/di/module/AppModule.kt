package com.example.fireflyhakanfirat.di.module

import android.app.Application
import android.content.Context
import com.example.fireflyhakanfirat.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideApp(app: Application): App {
        return app as App
    }
}