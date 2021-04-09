package com.bhaktaprogram.notehelper

import android.app.Application
import android.content.Context
import com.bhaktaprogram.coreapi.AppContextProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppContextComponent : AppContextProvider {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppContextComponent
    }

    companion object {

        fun create(application: Application): AppContextProvider =
            DaggerAppContextComponent
                .factory()
                .create(application.applicationContext)
    }
}