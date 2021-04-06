package com.bhaktaprogram.repository.di

import android.content.Context
import com.bhaktaprogram.repository.database.AppDatabaseContract
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class]
)
interface DatabaseComponent : AppDatabaseContract {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DatabaseComponent
    }

    companion object {

        fun create(context: Context): DatabaseComponent = DaggerDatabaseComponent
            .factory()
            .create(context)
    }
}