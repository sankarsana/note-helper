package com.bhaktaprogram.coreapi.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface NavigationProvider {

    fun provideCicerone(): Cicerone<Router>

    fun provideNavigatorHolder(): NavigatorHolder

    fun provideRouter(): Router
}