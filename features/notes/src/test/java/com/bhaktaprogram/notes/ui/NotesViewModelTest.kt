package com.bhaktaprogram.notes.ui

import com.bhaktaprogram.coreapi.navigation.EditNoteRouter
import com.bhaktaprogram.notes.domain.NotesInteractor
import com.bhaktaprogram.test.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val interactor = mockk<NotesInteractor>()
    private val router = mockk<EditNoteRouter>(relaxed = true)
    private val viewModel = NotesViewModel(interactor, router)

    @Test
    fun `should get notes`() = runBlocking {
        coEvery { interactor.getAll() } returns emptyList()

        viewModel.onOpened()

        coVerify { interactor.getAll() }
    }

    @Test
    fun `should pass id to router  to show specific note`() {
        val noteId = 123
        val note = NoteInfoUi(noteId.toString(), "", "")

        viewModel.onNoteClicked(note)

        verify { router.show(noteId) }
    }

    @Test
    fun `should pass -1 to router to show new note`() {
        viewModel.onAddClicked()

        verify { router.show(-1) }
    }
}