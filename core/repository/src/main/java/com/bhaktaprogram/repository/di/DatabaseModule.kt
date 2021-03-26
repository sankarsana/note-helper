package com.bhaktaprogram.repository.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.bhaktaprogram.repository.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "notes.db"

    @Provides
    @Singleton
    fun provideDatabase(context: Context) = Room
        .databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
        .also {
            Log.i("TAG", "create db")
        }
}