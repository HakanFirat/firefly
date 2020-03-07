package com.example.fireflyhakanfirat.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fireflyhakanfirat.data.db.AppDatabase
import com.example.fireflyhakanfirat.data.db.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(appDatabaseBuilder: RoomDatabase.Builder<AppDatabase?>): AppDatabase {
        return appDatabaseBuilder.build()
    }

    @Singleton
    @Provides
    fun provideAppDatabaseBuilder(context: Context?): RoomDatabase.Builder<AppDatabase> {
        return Room.databaseBuilder(context!!, AppDatabase::class.java, "hakan_firefly_challenge.db")
    }
}