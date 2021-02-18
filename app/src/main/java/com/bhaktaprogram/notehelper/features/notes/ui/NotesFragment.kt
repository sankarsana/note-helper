package com.bhaktaprogram.notehelper.features.notes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bhaktaprogram.notehelper.R
import com.bhaktaprogram.notehelper.features.notes.di.DaggerNotesComponent
import com.bhaktaprogram.notehelper.features.notes.domain.NoteInfo
import javax.inject.Inject

class NotesFragment : Fragment(R.layout.notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: NotesViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerNotesComponent.create().inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NotesViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner, ::updateState)
    }

    private fun updateState(list: List<NoteInfo>) {
        list.forEach {
            Log.i("TAG", it.toString())
        }
    }
}