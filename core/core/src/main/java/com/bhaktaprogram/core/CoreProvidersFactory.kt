package com.bhaktaprogram.core

import android.content.Context
import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import com.bhaktaprogram.coreimpl.di.DaggerCoreComponent
import com.bhaktaprogram.repository.di.DatabaseComponent

object CoreProvidersFactory {

    fun createRepositoryProvider(context: Context): RepositoryProvider {
        return DaggerCoreComponent
            .builder()
            .appDatabaseContract(DatabaseComponent.create(context))
            .build()
    }
}