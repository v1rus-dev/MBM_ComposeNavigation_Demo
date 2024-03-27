package yegor.cheprasov.mbm_decompose.decompose.dialog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_decompose.utils.BaseComponent
import yegor.cheprasov.mbm_design.di.utils.DialogType

class DeleteNoteDialogComponent(
    componentContext: ComponentContext,
    private val noteUid: Int,
    private val title: String,
    private val _onDismiss: () -> Unit,
    private val _onSuccess: () -> Unit
) : BaseComponent(componentContext), DialogComponent {

    private val notesRepository: NotesRepository by inject()

    override val dialogType: DialogType = DialogType.DeleteNote(
        noteUid = noteUid,
        title = title,
        onSuccess = { onSuccess() }
    )

    override fun onSuccess() {
        scope.launch {
            notesRepository.remove(noteUid)
            _onSuccess()
        }
    }

    override fun onDismiss() = _onDismiss.invoke()
}