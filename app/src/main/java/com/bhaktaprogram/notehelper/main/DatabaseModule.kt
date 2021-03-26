package com.bhaktaprogram.notehelper.main

import android.content.Context
import androidx.room.Room
import com.bhaktaprogram.notehelper.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {

        private const val DATABASE_NAME = "notes.db"

        @Provides
        @Singleton
        fun provideDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}