package yegor.cheprasov.mbm_data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.withContext
import yegor.cheprasov.mbm_database.dao.NoteDao
import yegor.cheprasov.mbm_database.entities.Note

class NotesRepository(
    private val noteDao: NoteDao,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun insert(note: Note) = withContext(coroutineDispatcher) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: Note) = withContext(coroutineDispatcher) {
        noteDao.update(note)
    }
    suspend fun remove(uid: Int) = withContext(coroutineDispatcher) {
        noteDao.removeNote(uid)
    }

    fun observeNotes(): Flow<List<Note>> = noteDao.observeNotes()
        .flowOn(coroutineDispatcher)

    fun observeFavoriteNotes(): Flow<List<Note>> = noteDao.observeFavoriteNotes()
        .flowOn(coroutineDispatcher)

    suspend fun updateFavoriteStatus(uid: Int, newStatus: Boolean) = withContext(coroutineDispatcher) {
        noteDao.updateFavoriteStatus(uid, newStatus)
    }

    suspend fun getNoteByUid(uid: Int) = withContext(coroutineDispatcher) {
        noteDao.getNote(uid)
    }

}