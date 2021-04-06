package com.bhaktaprogram.coreapi

import android.content.Context

interface AppContextProvider {

    fun getApplicationContext(): Context
}