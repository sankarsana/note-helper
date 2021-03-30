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

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun build(): AppComponent
    }

    companion object {

        fun create(application: Application): AppContextProvider =
            DaggerAppComponent.builder()
                .applicationContext(application.applicationContext)
                .build()
    }
}