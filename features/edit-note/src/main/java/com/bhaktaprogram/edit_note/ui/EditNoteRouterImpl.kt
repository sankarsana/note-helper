package com.bhaktaprogram.edit_note.ui

import com.bhaktaprogram.coreapi.navigation.EditNoteRouter
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class EditNoteRouterImpl @Inject constructor(
    private val router: Router
) : EditNoteRouter {

    override fun show(noteId: Int) {
        val screen = FragmentScreen { EditNotesFragment.newInstance(noteId) }
        router.navigateTo(screen)
    }
}