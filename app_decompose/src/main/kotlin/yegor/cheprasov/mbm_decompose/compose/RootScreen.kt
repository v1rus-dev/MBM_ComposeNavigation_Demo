package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import yegor.cheprasov.mbm_decompose.decompose.RealRootComponent
import yegor.cheprasov.mbm_decompose.decompose.RootComponent

@Composable
fun RootScreen(
    component: RealRootComponent
) {
    val stack by component.childStack.subscribeAsState()

    Children(stack = stack) { child ->
        when(val instance = child.instance) {
            is RootComponent.Child.Main -> MainScreen(component = instance.component)
            is RootComponent.Child.Note -> NoteScreen(component = instance.component)
        }
    }
}