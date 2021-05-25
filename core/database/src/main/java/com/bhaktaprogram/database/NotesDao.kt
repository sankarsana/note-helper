package com.bhaktaprogram.database

import androidx.room.*

@Dao
interface NotesDao {

    @Query("select * from notes")
    suspend fun getAll(): List<NoteEntity>

    @Query("select * from notes where id = :noteId")
    suspend fun getById(noteId: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}