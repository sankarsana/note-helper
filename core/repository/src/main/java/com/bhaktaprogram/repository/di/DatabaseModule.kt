package com.bhaktaprogram.repository.di

import android.content.Context
import androidx.room.Room
import com.bhaktaprogram.repository.database.AppDatabase
import com.bhaktaprogram.repository.database.AppDatabaseContract
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "notes.db"

    @Provides
    @Reusable
    fun provide(contract: AppDatabaseContract) = contract.notesDao()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabaseContract =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}