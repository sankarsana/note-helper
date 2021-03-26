package com.bhaktaprogram.notes.ui

import androidx.fragment.app.FragmentManager
import com.bhaktaprogram.coreapi.NotesMediator
import javax.inject.Inject

class NotesMediatorImpl @Inject constructor() : NotesMediator {

    override fun startNotesScreen(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, NotesFragment())
            .commit()
    }
}