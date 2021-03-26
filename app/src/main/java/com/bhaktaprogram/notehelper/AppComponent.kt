package com.bhaktaprogram.notehelper

import com.bhaktaprogram.core.CoreProvidersFactory
import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MediatorsBindsModule::class],
    dependencies = [RepositoryProvider::class]
)
interface AppComponent : ProvidersFacade {

    companion object {

        fun init(): AppComponent = DaggerAppComponent
            .builder()
            .repositoryProvider(CoreProvidersFactory.createRepositoryProvider())
            .build()
    }
}