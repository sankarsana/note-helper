package com.bhaktaprogram.core

import com.bhaktaprogram.coreapi.AppContextProvider
import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import com.bhaktaprogram.coreimpl.di.DaggerCoreComponent
import com.bhaktaprogram.repository.di.DatabaseComponent

object CoreProvidersFactory {

    fun createRepositoryProvider(appContextProvider: AppContextProvider): RepositoryProvider {
        val databaseContract = DatabaseComponent
            .create(appContextProvider.getApplicationContext())
        return DaggerCoreComponent.builder()
            .appDatabaseContract(databaseContract)
            .build()
    }
}