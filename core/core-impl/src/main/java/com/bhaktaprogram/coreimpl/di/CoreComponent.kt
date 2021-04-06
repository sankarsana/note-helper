package com.bhaktaprogram.coreimpl.di

import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import com.bhaktaprogram.database.AppDatabaseContract
import dagger.Component

@Component(
    modules = [CoreModule::class],
    dependencies = [AppDatabaseContract::class]
)
interface CoreComponent : RepositoryProvider