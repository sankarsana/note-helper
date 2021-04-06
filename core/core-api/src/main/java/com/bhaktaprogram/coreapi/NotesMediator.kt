package com.bhaktaprogram.coreapi

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface NotesMediator {

    fun startNotesScreen(@IdRes containerId: Int, fragmentManager: FragmentManager)
}