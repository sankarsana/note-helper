package com.bhaktaprogram.repository.di

import android.content.Context
import com.bhaktaprogram.repository.database.AppDatabaseContract
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent : AppDatabaseContract {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): DatabaseComponent
    }

    companion object {
        fun create(context: Context) = DaggerDatabaseComponent
            .builder()
            .setContext(context)
            .build()
    }
}