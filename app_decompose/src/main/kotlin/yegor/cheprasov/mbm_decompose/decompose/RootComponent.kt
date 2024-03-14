package yegor.cheprasov.mbm_decompose.decompose

import com.arkivanov.decompose.ComponentContext

class RootComponent(
    private val componentContext: ComponentContext
) : ComponentContext by componentContext {

}