package yegor.cheprasov.mbm_voyager.screenModels

import kotlinx.coroutines.launch
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

class NoteBottomSheetScreenModel(
    private val noteId: Int,
    private val notesRepository: NotesRepository
) : BaseScreenModel() {

    fun toggleFavorite(callback: () -> Unit) = scope.launch {
        val note = notesRepository.getNoteByUid(noteId) ?: return@launch callback()

        notesRepository.updateFavoriteStatus(uid = noteId, newStatus = note.isFavorite.not())
        callback()
    }

    fun removeNote(callback: () -> Unit) = scope.launch {
        notesRepository.remove(noteId)
        callback()
    }

    fun getNoteTitle(callback: (String) -> Unit) = scope.launch {
        val note = notesRepository.getNoteByUid(noteId) ?: return@launch

        callback(note.title)
    }

}