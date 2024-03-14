package yegor.cheprasov.mbm_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import yegor.cheprasov.mbm_database.entities.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM note WHERE uid = :uid")
    suspend fun getNote(uid: Int): Note?

    @Query("SELECT * FROM note")
    fun observeNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE isFavorite")
    fun observeFavoriteNotes(): Flow<List<Note>>

    @Query("DELETE FROM note WHERE uid = :uid")
    suspend fun removeNote(uid: Int)

    @Query("UPDATE note SET isFavorite = :newStatus WHERE uid = :uid")
    suspend fun updateFavoriteStatus(uid: Int, newStatus: Boolean)

}