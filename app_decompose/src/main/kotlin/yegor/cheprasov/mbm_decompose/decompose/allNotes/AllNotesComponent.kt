package yegor.cheprasov.mbm_decompose.decompose.allNotes

import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_decompose.decompose.PageComponent
import yegor.cheprasov.mbm_decompose.instance.AllNotesInstance

interface AllNotesComponent : PageComponent {

    val state: StateFlow<AllNotesInstance.State>

    fun showBottom()
}