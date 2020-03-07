package com.example.fireflyhakanfirat.di.module

import com.example.fireflyhakanfirat.data.source.UserDataSource
import com.example.fireflyhakanfirat.data.source.UserLocalDataSource
import com.example.fireflyhakanfirat.data.source.UserRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class DataSourceModule {

    @Binds
    @Named("local")
    @Singleton
    abstract fun provideUserLocalDataSource(userLocalDataSource: UserLocalDataSource?): UserDataSource?

    @Binds
    @Named("remote")
    @Singleton
    abstract fun provideUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSource?): UserDataSource?
}