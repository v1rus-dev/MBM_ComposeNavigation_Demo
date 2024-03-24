package yegor.cheprasov.mbm_decompose.decompose.allNotes

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import yegor.cheprasov.mbm_decompose.decompose.PageType
import yegor.cheprasov.mbm_decompose.instance.AllNotesInstance
import yegor.cheprasov.mbm_decompose.utils.BaseComponent

class RealAllNotesComponent(
    componentContext: ComponentContext
) : BaseComponent(componentContext), AllNotesComponent {

    override val type: PageType = PageType.AllNotes

    private val instance = instanceKeeper.getOrCreate {
        AllNotesInstance()
    }

    override val state: StateFlow<AllNotesInstance.State> = instance.state
    override fun showBottom() {

    }
}