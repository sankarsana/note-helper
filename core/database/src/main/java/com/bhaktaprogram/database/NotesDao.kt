package com.bhaktaprogram.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bhaktaprogram.database.database.NoteEntity

@Dao
interface NotesDao {

    @Query("select * from notes")
    suspend fun getAll(): List<NoteEntity>

    @Query("select * from notes where id = :noteId")
    suspend fun getById(noteId: Int): NoteEntity

    @Insert
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}