package com.bhaktaprogram.notehelper

import android.app.Application
import com.bhaktaprogram.core.CoreProvidersFactory
import com.bhaktaprogram.coreapi.AppContextProvider
import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [FacadeModule::class],
    dependencies = [AppContextProvider::class, RepositoryProvider::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent {
            val appContextProvider = AppComponent.create(application)
            val repositoryProvider = CoreProvidersFactory
                .createRepositoryProvider(appContextProvider)
            return DaggerFacadeComponent.builder()
                .appContextProvider(appContextProvider)
                .repositoryProvider(repositoryProvider)
                .build()
        }
    }
}