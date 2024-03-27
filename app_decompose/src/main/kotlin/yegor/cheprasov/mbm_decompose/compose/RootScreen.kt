package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import yegor.cheprasov.mbm_decompose.decompose.RealRootComponent
import yegor.cheprasov.mbm_decompose.decompose.RootComponent
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.NoteBottomSheet
import yegor.cheprasov.mbm_design.di.AppDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    component: RealRootComponent
) {
    val stack by component.childStack.subscribeAsState()

    val bottomSheet by component.bottomSlot.subscribeAsState()

    val dialog by component.dialogSlot.subscribeAsState()

    val sheetState = rememberModalBottomSheetState()

    if (dialog.child != null) {
        AppDialog(
            dialogType = dialog.child!!.instance.dialogType,
            onDismissRequest = { component.onDismissDialog() })
    }

    if (bottomSheet.child != null) {
        ModalBottomSheet(
            onDismissRequest = { component.onDismissBottomSheet() },
            dragHandle = { BottomSheetDefaults.DragHandle() },
            sheetState = sheetState
        ) {
            when (val instance = bottomSheet.child!!.instance) {
                is NoteBottomSheet -> NoteBottomSheet(instance)
            }
        }
    }

    Children(stack = stack) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.Main -> MainScreen(component = instance.component)
            is RootComponent.Child.Note -> NoteScreen(component = instance.component)
        }
    }
}