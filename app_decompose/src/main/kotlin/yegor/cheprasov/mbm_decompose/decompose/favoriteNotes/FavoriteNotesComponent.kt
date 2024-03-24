package yegor.cheprasov.mbm_decompose.decompose.favoriteNotes

import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_decompose.decompose.PageComponent
import yegor.cheprasov.mbm_decompose.instance.FavoriteNotesInstance

interface FavoriteNotesComponent : PageComponent {

    val state: StateFlow<FavoriteNotesInstance.State>

    fun showBottom()
}