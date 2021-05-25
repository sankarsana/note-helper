package com.bhaktaprogram.notes.ui

import com.bhaktaprogram.coreapi.navigation.NotesRouter
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class NotesRouterImpl @Inject constructor(
    private val router: Router
) : NotesRouter {

    override fun show() {
        val noteScreen = FragmentScreen { NotesFragment() }
        router.newRootScreen(noteScreen)
    }
}