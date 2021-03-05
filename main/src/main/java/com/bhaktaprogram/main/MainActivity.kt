package com.bhaktaprogram.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.coreapi.AppFacade
import com.bhaktaprogram.coreapi.NotesMediator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var notesMediator: NotesMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependencies()

        if (savedInstanceState == null) {
//            notesMediator.startNotesScreen(R.id.fragments_container, supportFragmentManager)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragments_container, CalendarFragment())
                .commit()
        }
    }

    private fun injectDependencies() {
        val providersFacade = (application as AppFacade).getProvidersFacade()
        MainComponent.create(providersFacade).inject(this)
    }
}