package com.bhaktaprogram.edit_note.ui

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.edit_note.domain.EditNoteInteractor
import com.bhaktaprogram.test.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class EditNotesViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val interactor = mockk<EditNoteInteractor>()
    private val viewModel = EditNotesViewModel(interactor)

    @Test
    fun `should get note from repository`() = runBlocking {
        val note = Note(123, "title", "text")
        coEvery { interactor.getNote(note.id) } returns note

        viewModel.onOpened(note.id)

        coVerify { interactor.getNote(note.id) }
    }

    @Test
    fun `should not get note from repository`() = runBlocking {

        viewModel.onOpened(-1)

        coVerify(exactly = 0) { interactor.getNote(any()) }
    }
}