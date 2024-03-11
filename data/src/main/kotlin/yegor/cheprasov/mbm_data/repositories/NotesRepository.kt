package yegor.cheprasov.mbm_data.repositories

import kotlinx.coroutines.CoroutineDispatcher
import yegor.cheprasov.mbm_database.dao.NoteDao

class NotesRepository(
    private val noteDao: NoteDao,
    private val coroutineDispatcher: CoroutineDispatcher
) {
}