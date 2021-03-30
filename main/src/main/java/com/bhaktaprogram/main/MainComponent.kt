package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.MediatorsProvider
import com.bhaktaprogram.coreapi.ProvidersFacade
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