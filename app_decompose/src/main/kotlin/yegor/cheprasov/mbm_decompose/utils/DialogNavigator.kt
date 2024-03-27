package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.router.slot.SlotNavigation
import yegor.cheprasov.mbm_decompose.decompose.DialogConfig

object DialogNavigator {

    private var navigator: SlotNavigation<DialogConfig>? = null

    fun setNavigator(navigator: SlotNavigation<DialogConfig>) {
        this.navigator = navigator
    }

    fun getNavigator(): SlotNavigation<DialogConfig>? = navigator

    fun getNavigatorOrThrow(): SlotNavigation<DialogConfig> = navigator ?: throw NullPointerException("Navigator must be not null")

}