package com.bhaktaprogram.edit_note.domain

import com.bhaktaprogram.coreapi.dto.Note
import com.bhaktaprogram.coreapi.repository.NoteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class EditNoteInteractorTest {

    private val repository = mockk<NoteRepository>(relaxed = true)
    private val interactor = EditNoteInteractor(repository)

    @Test
    fun `should get note from repository`() = runBlocking {
        val note = Note(123, "", "")
        coEvery { repository.getById(note.id) } returns note

        val actualNote = interactor.getNote(note.id)

        assertEquals(note, actualNote)
    }

    @Test
    fun `should save note by repository`() = runBlocking {
        val note = Note(123, "", "")

        interactor.save(note)

        coVerify { repository.save(note) }
    }
}