package com.bhaktaprogram.notehelper

import android.app.Application
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.ProvidersFacade
import timber.log.Timber

class App : Application(), AppFacade {

    private var providersFacade: ProvidersFacade? = null

    override fun onCreate() {
        super.onCreate()
        initTimber()
        getProvidersFacade()
    }

    override fun getProvidersFacade(): ProvidersFacade {
        return providersFacade
            ?: FacadeComponent.init(this).also { providersFacade = it }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}