package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.navigation.MainRouter
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val router: Router
) : MainRouter {

    override fun showNotes() {
        TODO()
    }


}