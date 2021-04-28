package com.bhaktaprogram.notes.domain

import com.bhaktaprogram.coreapi.dto.NoteInfo
import com.bhaktaprogram.coreapi.repository.NoteRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class NotesInteractorTest {

    private val repository = mockk<NoteRepository>()
    private val interactor = NotesInteractor(repository)

    @Test
    fun `should get notes from repository`() = runBlocking {
        val notes = listOf(NoteInfo(1, "title", "text"))
        coEvery { repository.getNotesInfo() } returns notes

        val result = interactor.getAll()

        assertEquals(notes, result)
    }
}