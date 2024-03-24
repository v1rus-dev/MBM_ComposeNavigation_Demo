package yegor.cheprasov.mbm_decompose.decompose.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.essenty.backhandler.BackCallback
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import yegor.cheprasov.mbm_decompose.instance.NoteInstance
import yegor.cheprasov.mbm_decompose.utils.BaseComponent
import yegor.cheprasov.mbm_decompose.utils.RootNavigator

@OptIn(ExperimentalFoundationApi::class)
class RealNoteComponent(
    componentContext: ComponentContext,
    val noteUid: Int? = null,
    val initTitle: String = ""
) : BaseComponent(componentContext), NoteComponent {

    private val instance: NoteInstance by inject<NoteInstance> { parametersOf(noteUid, initTitle) }


    private val backCallback = BackCallback {
        onBack()
    }

    init {
        backHandler.register(backCallback)
        instance.toString()
    }

    override val title: TextFieldState = instance.body

    override val body: TextFieldState = instance.title

    override fun onBack() {
        instance.save()
        RootNavigator.getNavigatorOrThrow().pop()
    }

}