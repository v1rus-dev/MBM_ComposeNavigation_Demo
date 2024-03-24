package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackCallback
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

open class BaseComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    protected val dispatcherMain = Dispatchers.Main
    protected val dispatcherIO = Dispatchers.IO

    protected var scope = CoroutineScope(dispatcherMain, lifecycle)

//    open val callbackEnabled = false
//    open val priority = Int.MIN_VALUE
//    protected val backCallback = BackCallback(isEnabled = callbackEnabled, priority = priority) {
//        onBack()
//    }

    open fun onBack() = Unit
}