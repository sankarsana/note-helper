package com.bhaktaprogram.notehelper

import android.app.Application
import android.content.Context
import com.bhaktaprogram.coreapi.AppContextProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent : AppContextProvider {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context): AppComponent
    }

    companion object {

        fun create(application: Application): AppContextProvider =
            DaggerAppComponent
                .factory()
                .create(application.applicationContext)
    }
}