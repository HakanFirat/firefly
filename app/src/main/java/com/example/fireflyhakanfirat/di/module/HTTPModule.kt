package com.example.fireflyhakanfirat.di.module

import com.example.fireflyhakanfirat.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class HTTPModule {

    @Singleton
    @Named("defaultOkHttpClient")
    @Provides
    fun provideOkHttpClient(@Named("defaultOkHttpBuilder") builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Singleton
    @Named("defaultOkHttpBuilder")
    @Provides
    fun provideOkHttpBuilder(loggingInterceptor: Interceptor?): OkHttpClient.Builder {
        val builder =
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .followRedirects(true)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor!!)
        }
        return builder
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}