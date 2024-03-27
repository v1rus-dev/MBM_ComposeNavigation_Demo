package yegor.cheprasov.mbm_decompose.decompose

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.BottomSheet
import yegor.cheprasov.mbm_decompose.decompose.dialog.DialogComponent
import yegor.cheprasov.mbm_decompose.decompose.main.MainComponent
import yegor.cheprasov.mbm_decompose.decompose.note.NoteComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    val dialogSlot: Value<ChildSlot<*, DialogComponent>>

    val bottomSlot: Value<ChildSlot<*, BottomSheet>>

    fun onDismissDialog()

    fun onDismissBottomSheet()

    sealed interface Child {

        class Main(val component: MainComponent) : Child

        class Note(val component: NoteComponent) : Child

    }

}