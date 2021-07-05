package com.bhaktaprogram.edit_note.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bhaktaprogram.coreapi.extensions.getProviderFacade
import com.bhaktaprogram.edit_note.R
import com.bhaktaprogram.edit_note.databinding.EditNotesFragmentBinding
import com.bhaktaprogram.edit_note.di.EditNoteComponent
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class EditNotesFragment : Fragment(R.layout.edit_notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val binding by viewBinding(EditNotesFragmentBinding::bind)
    private val viewModel by viewModels<EditNotesViewModel> { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EditNoteComponent
            .create(getProviderFacade())
            .inject(this)

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect(::updateState)
        }

        lifecycle.addObserver(viewModel)

        val noteId = arguments?.getInt(NOTE_ID) ?: -1
        viewModel.onOpened(noteId)

        with(binding) {
            title.addTextChangedListener { title ->
                viewModel.onTitleChanged(title?.toString() ?: return@addTextChangedListener)
            }
            text.addTextChangedListener { text ->
                viewModel.onTextChanged(text?.toString() ?: return@addTextChangedListener)
            }
        }
    }

    private fun updateState(note: EditNoteUi) = with(binding) {
        if (title.text.toString() != note.title) {
            title.setText(note.title)
        }
        if (text.text.toString() != note.text) {
            text.setText(note.text)
        }
    }

    companion object {

        private const val NOTE_ID = "note_id"

        fun newInstance(noteId: Int) = EditNotesFragment().apply {
            arguments = bundleOf(NOTE_ID to noteId)
        }
    }

}