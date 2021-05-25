package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)

    companion object {

        fun create(appFacade: AppFacade): MainComponent {
            return DaggerMainComponent
                .builder()
                .providersFacade(appFacade.getProvidersFacade())
                .build()
        }
    }
}