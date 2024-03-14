package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent

class BaseComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    protected val dispatcherMain = Dispatchers.Main
    protected val dispatcherIO = Dispatchers.IO

    protected var scope = CoroutineScope(dispatcherMain, lifecycle)


}