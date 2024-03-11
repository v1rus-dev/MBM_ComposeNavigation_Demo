package yegor.cheprasov.mbm_voyager.utils

import cafe.adriel.voyager.navigator.Navigator

object RootNavigator {

    private var rootNavigator: Navigator? = null

    fun setNavigator(navigator: Navigator) {
        rootNavigator = navigator
    }

    fun getRootNavigator(): Navigator? = rootNavigator

    fun getRootNavigatorOrThrow(): Navigator = rootNavigator ?: throw NullPointerException("Root navigator must not be null")

}