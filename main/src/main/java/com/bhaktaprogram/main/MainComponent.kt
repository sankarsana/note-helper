package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.ProvidersFacade
import com.bhaktaprogram.coreapi.navigation.MediatorsProvider
import dagger.Component

@Component(
    dependencies = [MediatorsProvider::class]
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent =
            DaggerMainComponent.builder()
                .mediatorsProvider(providersFacade)
                .build()
    }
}