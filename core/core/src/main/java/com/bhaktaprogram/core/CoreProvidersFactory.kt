package com.bhaktaprogram.core

import com.bhaktaprogram.coreapi.repository.RepositoryProvider
import com.bhaktaprogram.coreimpl.di.DaggerCoreComponent

object CoreProvidersFactory {

    fun createRepositoryProvider(): RepositoryProvider {
        return DaggerCoreComponent.builder().build()
    }
}