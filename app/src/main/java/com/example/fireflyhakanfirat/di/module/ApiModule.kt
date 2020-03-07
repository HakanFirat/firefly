package com.example.fireflyhakanfirat.di.module

import com.example.fireflyhakanfirat.BuildConfig
import com.example.fireflyhakanfirat.api.ApiService
import com.example.fireflyhakanfirat.api.ItemTypeAdapterFactory
import com.example.fireflyhakanfirat.data.LiveDataCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [HTTPModule::class])
class ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        liveDataCallAdapterFactory: LiveDataCallAdapterFactory,
                        @Named("defaultOkHttpClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(liveDataCallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideGson(gsonBuilder: GsonBuilder): Gson {
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder(itemTypeAdapterFactory: ItemTypeAdapterFactory): GsonBuilder {
        val gsonBuilder = GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
            .registerTypeAdapterFactory(itemTypeAdapterFactory)
        if (BuildConfig.DEBUG) {
            gsonBuilder.setPrettyPrinting()
        }
        return gsonBuilder
    }

    @Singleton
    @Provides
    fun provideItemTypeAdapterFactory(): ItemTypeAdapterFactory {
        return ItemTypeAdapterFactory()
    }

}