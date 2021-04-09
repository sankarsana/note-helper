package com.bhaktaprogram.main

import android.app.Application
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.navigation.NavigationProvider
import com.bhaktaprogram.coreapi.navigation.RoutersProvider
import dagger.Component

@Component(
    dependencies = [RoutersProvider::class, NavigationProvider::class],
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    companion object {

        fun create(application: Application): MainComponent {
            val providersFacade = (application as AppFacade).getProvidersFacade()
            return DaggerMainComponent
                .builder()
                .routersProvider(providersFacade)
                .navigationProvider(providersFacade)
                .build()
        }
    }
}