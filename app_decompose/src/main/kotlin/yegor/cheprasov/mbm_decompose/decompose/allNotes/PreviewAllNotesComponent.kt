package yegor.cheprasov.mbm_decompose.decompose.allNotes

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_database.entities.Note
import yegor.cheprasov.mbm_decompose.decompose.PageType
import yegor.cheprasov.mbm_decompose.instance.AllNotesInstance

class PreviewAllNotesComponent : AllNotesComponent {
    override val state: StateFlow<AllNotesInstance.State>
        get() = MutableStateFlow(AllNotesInstance.State())

    override fun showBottom(note: Note) = Unit

    override val type: PageType
        get() = PageType.AllNotes
}