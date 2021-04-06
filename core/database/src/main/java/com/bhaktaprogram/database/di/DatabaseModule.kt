package com.bhaktaprogram.database.di

import android.content.Context
import androidx.room.Room
import com.bhaktaprogram.database.AppDatabase
import com.bhaktaprogram.database.AppDatabaseContract
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "notes.db"

    @Provides
    @Reusable
    fun provideNotesDao(contract: AppDatabaseContract) = contract.notesDao()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabaseContract =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}