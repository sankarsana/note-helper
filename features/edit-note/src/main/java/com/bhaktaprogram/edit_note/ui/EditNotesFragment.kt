package com.bhaktaprogram.edit_note.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bhaktaprogram.edit_note.R
import com.bhaktaprogram.edit_note.databinding.EditNotesFragmentBinding
import javax.inject.Inject

class EditNotesFragment : Fragment(R.layout.edit_notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val binding by viewBinding(EditNotesFragmentBinding::bind)
    private val viewModel by viewModels<EditNotesViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        private const val NOTE_ID = "note_id"

        fun newInstance(noteId: Int) = EditNotesFragment().apply {
            arguments = bundleOf(NOTE_ID to noteId)
        }
    }

}