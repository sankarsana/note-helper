package com.bhaktaprogram.main

import com.bhaktaprogram.coreapi.navigation.NotesRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: NotesRouter
) {
    fun onOpened() {
        router.show()
    }
}