package yegor.cheprasov.mbm_decompose.decompose.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import yegor.cheprasov.mbm_decompose.decompose.PageComponent
import yegor.cheprasov.mbm_decompose.decompose.allNotes.RealAllNotesComponent
import yegor.cheprasov.mbm_decompose.decompose.favoriteNotes.RealFavoriteNotesComponent
import yegor.cheprasov.mbm_decompose.utils.BaseComponent

class RealMainComponent(
    componentContext: ComponentContext
) : BaseComponent(componentContext), MainComponent {

    private val navigation = StackNavigation<Config>()

    override val pages: Value<ChildStack<*, PageComponent>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        handleBackButton = true,
        initialStack = {
            listOf(Config.AllNotes)
        }
    ) { configuration, componentContext ->
        when(configuration) {
            Config.AllNotes -> RealAllNotesComponent(componentContext)
            Config.FavoriteNotes -> RealFavoriteNotesComponent(componentContext)
        }
    }

    override fun onAllClicked() {
        navigation.bringToFront(Config.AllNotes)
    }

    override fun onFavoriteClicked() {
        navigation.bringToFront(Config.FavoriteNotes)
    }

    @Serializable
    sealed interface Config {

        data object AllNotes : Config

        data object FavoriteNotes : Config

    }

}