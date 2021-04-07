package com.bhaktaprogram.notes.ui

import androidx.fragment.app.FragmentManager
import com.bhaktaprogram.coreapi.navigation.NotesMediator
import javax.inject.Inject

class NotesMediatorImpl @Inject constructor() : NotesMediator {

    override fun startNotes(containerId: Int, fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(containerId, NotesFragment())
            .commit()
    }
}