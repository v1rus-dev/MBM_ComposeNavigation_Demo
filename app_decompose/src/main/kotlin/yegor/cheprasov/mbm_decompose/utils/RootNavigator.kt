package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.router.stack.StackNavigation
import yegor.cheprasov.mbm_decompose.decompose.RootConfig

object RootNavigator {

    private var navigator: StackNavigation<RootConfig>? = null

    fun setNavigator(navigator: StackNavigation<RootConfig>) {
        this.navigator = navigator
    }

    fun getNavigator(): StackNavigation<RootConfig>? = navigator

    fun getNavigatorOrThrow(): StackNavigation<RootConfig> = navigator ?: throw NullPointerException("Navigator must be not null")

}