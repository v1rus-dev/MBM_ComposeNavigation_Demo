package yegor.cheprasov.mbm_decompose.utils

import com.arkivanov.decompose.router.slot.SlotNavigation
import yegor.cheprasov.mbm_decompose.decompose.BottomSheetConfig

object BottomSheetNavigator {
    private var navigator: SlotNavigation<BottomSheetConfig>? = null

    fun setNavigator(navigator: SlotNavigation<BottomSheetConfig>) {
        this.navigator = navigator
    }

    fun getNavigator(): SlotNavigation<BottomSheetConfig>? = navigator

    fun getNavigatorOrThrow(): SlotNavigation<BottomSheetConfig> = navigator ?: throw NullPointerException("Navigator must be not null")
}