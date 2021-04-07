package com.bhaktaprogram.coreapi.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface EditNoteMediator {

    fun startEditNotes(@IdRes containerId: Int, fragmentManager: FragmentManager, noteId: Int)
}