package yegor.cheprasov.mbm_decompose.decompose.bottomSheets

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.activate
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_decompose.decompose.DialogConfig
import yegor.cheprasov.mbm_decompose.utils.BaseComponent
import yegor.cheprasov.mbm_decompose.utils.DialogNavigator

class NoteBottomSheet(
    componentContext: ComponentContext,
    private val noteId: Int,
    private val isFavorite: Boolean,
    private val title: String,
    private val onDismiss: () -> Unit,
) : BaseComponent(componentContext), BottomSheet {

    private val notesRepository: NotesRepository by inject()

    override val noteIsFavorite: Boolean = isFavorite

    override fun toggleFavorite() {
        scope.launch {
            val note = notesRepository.getNoteByUid(noteId) ?: return@launch onDismiss()

            notesRepository.updateFavoriteStatus(noteId, newStatus = note.isFavorite.not())
            onHide()
        }
    }

    override fun onRemoveNote() {
        DialogNavigator.getNavigator()?.activate(DialogConfig.DeleteNote(noteId, title))
    }

    override fun onHide() {
        onDismiss.invoke()
    }
}