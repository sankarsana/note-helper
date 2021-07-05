package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }
}