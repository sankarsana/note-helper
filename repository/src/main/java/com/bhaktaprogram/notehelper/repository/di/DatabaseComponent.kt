package com.bhaktaprogram.notehelper.repository.di

import android.content.Context
import androidx.room.Room
import com.bhaktaprogram.notehelper.repository.database.AppDatabase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): DatabaseComponent
    }
}

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