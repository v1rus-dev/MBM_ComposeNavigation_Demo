package yegor.cheprasov.mbm_decompose.decompose.favoriteNotes

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_decompose.decompose.PageType
import yegor.cheprasov.mbm_decompose.instance.FavoriteNotesInstance

class PreviewFavoriteNotesComponent : FavoriteNotesComponent {
    override val state: StateFlow<FavoriteNotesInstance.State>
        get() = MutableStateFlow(FavoriteNotesInstance.State())

    override fun showBottom() = Unit

    override val type: PageType
        get() = PageType.FavoriteNotes
}