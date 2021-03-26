package com.bhaktaprogram.notehelper

import com.bhaktaprogram.coreapi.ProvidersFacade
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [MediatorsBindsModule::class]
)
interface AppComponent : ProvidersFacade