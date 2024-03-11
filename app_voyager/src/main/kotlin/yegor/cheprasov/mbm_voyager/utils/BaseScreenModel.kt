package yegor.cheprasov.mbm_voyager.utils

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseScreenModel : ScreenModel {

    protected val dispatcherMain = Dispatchers.Main
    protected val dispatcherIO = Dispatchers.IO

    protected var scope = CoroutineScope(SupervisorJob() + dispatcherMain)

    override fun onDispose() {
        super.onDispose()
        scope.cancel()
    }

}