package com.bhaktaprogram.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bhaktaprogram.database.database.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(), AppDatabaseContract