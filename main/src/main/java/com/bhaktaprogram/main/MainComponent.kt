package com.bhaktaprogram.main

import android.app.Application
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.navigation.MediatorsProvider
import com.bhaktaprogram.coreapi.navigation.NavigationProvider
import com.bhaktaprogram.coreapi.navigation.RoutersProvider
import dagger.Component

@Component(
    dependencies = [MediatorsProvider::class, RoutersProvider::class, NavigationProvider::class],
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    companion object {

        fun create(application: Application): MainComponent {
            val providersFacade = (application as AppFacade).getProvidersFacade()
            return DaggerMainComponent
                .builder()
                .mediatorsProvider(providersFacade)
                .routersProvider(providersFacade)
                .navigationProvider(providersFacade)
                .build()
        }
    }
}