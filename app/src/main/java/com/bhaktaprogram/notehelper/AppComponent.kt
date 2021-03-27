package com.bhaktaprogram.notehelper

import android.content.Context
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

        fun init(context: Context): AppComponent {
            val createRepositoryProvider = CoreProvidersFactory.createRepositoryProvider(context)
            return DaggerAppComponent
                .builder()
                .repositoryProvider(createRepositoryProvider)
                .build()
        }
    }
}