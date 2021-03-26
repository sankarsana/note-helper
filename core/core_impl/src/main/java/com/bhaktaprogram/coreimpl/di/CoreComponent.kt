package com.bhaktaprogram.coreimpl.di

import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import dagger.Component

@Component(
    modules = [CoreModule::class]
)
interface CoreComponent : RepositoryProvider