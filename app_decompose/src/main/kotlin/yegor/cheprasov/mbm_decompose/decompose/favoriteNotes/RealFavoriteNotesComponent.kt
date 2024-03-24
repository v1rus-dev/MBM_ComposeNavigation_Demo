package yegor.cheprasov.mbm_decompose.decompose.favoriteNotes

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_decompose.decompose.PageType
import yegor.cheprasov.mbm_decompose.instance.FavoriteNotesInstance
import yegor.cheprasov.mbm_decompose.utils.BaseComponent
import yegor.cheprasov.mbm_decompose.utils.injectInstance

class RealFavoriteNotesComponent(
    componentContext: ComponentContext
) : BaseComponent(componentContext), FavoriteNotesComponent {

    override val type: PageType = PageType.FavoriteNotes

    private val instance: FavoriteNotesInstance by injectInstance(::FavoriteNotesInstance)

    override val state: StateFlow<FavoriteNotesInstance.State> = instance.state

    override fun showBottom() {

    }
}