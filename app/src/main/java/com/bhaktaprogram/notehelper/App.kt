package com.bhaktaprogram.notehelper

import android.app.Application
import com.bhaktaprogram.repository.di.DaggerDatabaseComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerDatabaseComponent.builder().setContext(this).build()
    }
}