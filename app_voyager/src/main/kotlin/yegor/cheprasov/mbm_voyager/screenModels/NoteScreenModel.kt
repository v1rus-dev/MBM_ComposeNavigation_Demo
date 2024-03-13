package yegor.cheprasov.mbm_voyager.screenModels

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.setTextAndPlaceCursorAtEnd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_database.entities.Note
import yegor.cheprasov.mbm_design.di.utils.DialogType
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

@OptIn(ExperimentalFoundationApi::class)
class NoteScreenModel(
    private val noteUid: Int? = null,
    private val initTitle: String = "",
    private val notesRepository: NotesRepository
) : BaseScreenModel() {

    private val mutableDialog = MutableStateFlow<DialogType?>(null)

    val dialog: StateFlow<DialogType?> = mutableDialog

    val title = TextFieldState(initTitle)
    val body = TextFieldState()

    init {
        loadNote()
    }

    fun save() = scope.launch {
        if (title.text.isEmpty() && body.text.isEmpty()) {

            if (noteUid != null) {
                notesRepository.remove(noteUid)
            }

            return@launch
        }

        val title = if (title.text.toString().isNotEmpty()) {
            title.text.toString()
        } else {
            "Заголовок"
        }

        if (noteUid != null) {
            notesRepository.update(
                createNote(
                    uid = noteUid,
                    title = title,
                    body = body.text.toString()
                )
            )
            return@launch
        } else {
            notesRepository.insert(createNote(title = title, body = body.text.toString()))
            return@launch
        }

    }

    fun clearDialog() {
        mutableDialog.update { null }
    }

    private fun createNote(
        uid: Int = 0,
        title: String,
        body: String,
    ): Note = Note(uid = uid, title = title, body = body, isFavorite = false)

    private fun loadNote() = scope.launch {
        if (noteUid == null) return@launch

        val note = notesRepository.getNoteByUid(noteUid) ?: return@launch

        body.setTextAndPlaceCursorAtEnd(note.body)
    }

}