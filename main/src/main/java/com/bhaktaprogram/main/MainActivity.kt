package com.bhaktaprogram.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhaktaprogram.notehelper.notes.ui.NotesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragments_container, NotesFragment())
                .commit()
        }
    }
}