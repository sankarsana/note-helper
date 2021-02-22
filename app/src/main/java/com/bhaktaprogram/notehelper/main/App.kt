package com.bhaktaprogram.notehelper.main

import android.app.Application
import com.bhaktaprogram.notehelper.db.AppDatabase
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        initDatabase()
    }

    private fun initDatabase() {
        DaggerAppComponent.builder()
            .setContext(this)
            .build()
            .inject(this)
    }
}