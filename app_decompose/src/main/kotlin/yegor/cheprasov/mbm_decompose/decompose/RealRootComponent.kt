package yegor.cheprasov.mbm_decompose.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.BottomSheet
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.NoteBottomSheet
import yegor.cheprasov.mbm_decompose.decompose.dialog.DeleteNoteDialogComponent
import yegor.cheprasov.mbm_decompose.decompose.dialog.DialogComponent
import yegor.cheprasov.mbm_decompose.decompose.main.RealMainComponent
import yegor.cheprasov.mbm_decompose.decompose.note.RealNoteComponent
import yegor.cheprasov.mbm_decompose.utils.BottomSheetNavigator
import yegor.cheprasov.mbm_decompose.utils.DialogNavigator
import yegor.cheprasov.mbm_decompose.utils.RootNavigator

class RealRootComponent(
    private val componentContext: ComponentContext
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<RootConfig>().also {
        RootNavigator.setNavigator(it)
    }

    private val dialogNavigation = SlotNavigation<DialogConfig>().also {
        DialogNavigator.setNavigator(it)
    }

    private val bottomSheetNavigation = SlotNavigation<BottomSheetConfig>().also {
        BottomSheetNavigator.setNavigator(it)
    }

    override fun onDismissDialog() {
        dialogNavigation.dismiss()
    }

    override fun onDismissBottomSheet() {
        bottomSheetNavigation.dismiss()
    }

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.Main,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override val dialogSlot: Value<ChildSlot<*, DialogComponent>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        key = "DialogChildSlot"
    ) { configuration: DialogConfig, componentContext: ComponentContext ->
        when (configuration) {
            is DialogConfig.DeleteNote -> DeleteNoteDialogComponent(componentContext = componentContext,
                noteUid = configuration.noteUid,
                title = configuration.title,
                _onDismiss = {
                    dialogNavigation.dismiss()
                },
                _onSuccess = {
                    dialogNavigation.dismiss {
                        if (it) {
                            bottomSheetNavigation.dismiss()
                        }
                    }
                })
        }
    }

    override val bottomSlot: Value<ChildSlot<*, BottomSheet>> = childSlot(
        source = bottomSheetNavigation,
        serializer = BottomSheetConfig.serializer(),
        key = "BottomSheetChildSlot"
    ) { configuration, componentContext ->
        when (configuration) {
            is BottomSheetConfig.NoteBottom -> NoteBottomSheet(componentContext,
                noteId = configuration.noteId,
                isFavorite = configuration.isFavorite,
                title = configuration.title,
                onDismiss = {
                    bottomSheetNavigation.dismiss()
                }
            )
        }
    }

    private fun createChild(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            RootConfig.Main -> RootComponent.Child.Main(
                component = RealMainComponent(
                    componentContext
                )
            )

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

@Serializable
sealed interface DialogConfig {
    data class DeleteNote(
        val noteUid: Int,
        val title: String
    ) : DialogConfig
}

@Serializable
sealed interface BottomSheetConfig {
    data class NoteBottom(
        val noteId: Int,
        val isFavorite: Boolean,
        val title: String
    ) : BottomSheetConfig
}