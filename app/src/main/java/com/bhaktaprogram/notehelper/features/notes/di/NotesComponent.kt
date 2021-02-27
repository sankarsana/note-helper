package com.bhaktaprogram.notehelper.features.notes.di

import com.bhaktaprogram.notehelper.features.notes.ui.NotesFragment
import dagger.Component

@Component(modules = [NotesModule::class])
interface NotesComponent {

    fun inject(fragment: NotesFragment)
}