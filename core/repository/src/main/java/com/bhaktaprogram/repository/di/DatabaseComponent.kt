package com.bhaktaprogram.repository.di

import com.bhaktaprogram.coreapi.AppContextProvider
import com.bhaktaprogram.repository.database.AppDatabaseContract
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class],
    dependencies = [AppContextProvider::class]
)
interface DatabaseComponent : AppDatabaseContract {

    companion object {

        fun create(appContextProvider: AppContextProvider): DatabaseComponent =
            DaggerDatabaseComponent.builder()
                .appContextProvider(appContextProvider)
                .build()
    }
}