package yegor.cheprasov.mbm_decompose.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import yegor.cheprasov.mbm_decompose.decompose.main.RealMainComponent
import yegor.cheprasov.mbm_decompose.decompose.note.RealNoteComponent
import yegor.cheprasov.mbm_decompose.utils.RootNavigator

class RealRootComponent(
    private val componentContext: ComponentContext
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<RootConfig>().also {
        RootNavigator.setNavigator(it)
    }

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.Main,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            RootConfig.Main -> RootComponent.Child.Main(component = RealMainComponent(componentContext))
            is RootConfig.Note -> RootComponent.Child.Note(
                component = RealNoteComponent(
                    componentContext,
                    noteUid = config.noteUid,
                    initTitle = config.initTitle
                )
            )
        }
}

@Serializable
sealed interface RootConfig {

    data object Main : RootConfig

    data class Note(
        val noteUid: Int? = null,
        val initTitle: String = ""
    ) : RootConfig

}