package com.bhaktaprogram.edit_note.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bhaktaprogram.coreapi.extensions.get
import com.bhaktaprogram.edit_note.R
import com.bhaktaprogram.edit_note.databinding.EditNotesFragmentBinding
import javax.inject.Inject

class EditNotesFragment : Fragment(R.layout.edit_notes_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { viewModelFactory.get(this, EditNotesViewModel::class.java) }
    private val binding by viewBinding(EditNotesFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = EditNotesFragment()
    }

}