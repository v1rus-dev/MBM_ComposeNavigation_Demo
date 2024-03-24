package yegor.cheprasov.mbm_voyager.screenModels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import yegor.cheprasov.mbm_data.repositories.NotesRepository

class NoteBottomSheetScreenModel(
    private val noteId: Int,
    private val notesRepository: NotesRepository
) : ScreenModel {

    fun toggleFavorite(callback: () -> Unit) = screenModelScope.launch {
        val note = notesRepository.getNoteByUid(noteId) ?: return@launch callback()

        notesRepository.updateFavoriteStatus(uid = noteId, newStatus = note.isFavorite.not())
        callback()
    }

    fun removeNote(callback: () -> Unit) = screenModelScope.launch {
        notesRepository.remove(noteId)
        callback()
    }

    fun getNoteTitle(callback: (String) -> Unit) = screenModelScope.launch {
        val note = notesRepository.getNoteByUid(noteId) ?: return@launch

        callback(note.title)
    }

}