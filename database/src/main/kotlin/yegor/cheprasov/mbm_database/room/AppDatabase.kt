package yegor.cheprasov.mbm_database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import yegor.cheprasov.mbm_database.dao.NoteDao
import yegor.cheprasov.mbm_database.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}