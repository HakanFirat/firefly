package com.example.fireflyhakanfirat.di

import android.app.Application
import com.example.fireflyhakanfirat.App
import com.example.fireflyhakanfirat.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        DataSourceModule::class,
        ApiModule::class,
        DomainModule::class,
        ViewModelModule::class,
        DatabaseModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}