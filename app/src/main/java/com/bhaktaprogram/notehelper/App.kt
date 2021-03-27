package com.bhaktaprogram.notehelper

import android.app.Application
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade

class App : Application(), AppFacade {

    private var providersFacade: ProvidersFacade? = null

    override fun onCreate() {
        super.onCreate()
        getProvidersFacade()
    }

    override fun getProvidersFacade(): ProvidersFacade = providersFacade
        ?: AppComponent.init(this).also { providersFacade = it }
}