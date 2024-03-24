package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent

open class BaseInstance : InstanceKeeper.Instance, KoinComponent {

    protected val dispatcherMain = Dispatchers.Main
    protected val dispatcherIO = Dispatchers.IO
    protected val scope = CoroutineScope(SupervisorJob() + dispatcherMain)

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}

inline fun <reified T : InstanceKeeper.Instance> ComponentContext.injectInstance(
    crossinline factory: () -> T
): Lazy<T> = lazy { instanceKeeper.getOrCreate(factory) }

inline fun <reified T : InstanceKeeper.Instance> ComponentContext.injectInstance(
    key: Any,
    crossinline factory: () -> T
): Lazy<T> = lazy { instanceKeeper.getOrCreate(key = key, factory) }