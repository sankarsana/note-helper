package com.bhaktaprogram.coreapi.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface NotesMediator {

    fun startNotes(@IdRes containerId: Int, fragmentManager: FragmentManager)
}