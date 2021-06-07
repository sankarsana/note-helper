package com.bhaktaprogram.edit_note

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomAppRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, TestApp::class.java.name, context)
    }
}