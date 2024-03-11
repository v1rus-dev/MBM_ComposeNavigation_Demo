package yegor.cheprasov.mbm_voyager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import yegor.cheprasov.mbm_voyager.ui.theme.MBM_ComposeNavigation_DemoTheme
import yegor.cheprasov.mbm_voyager.utils.RootNavigator
import yegor.cheprasov.mbm_voyager.voyager.RootScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MBM_ComposeNavigation_DemoTheme {
                BottomSheetNavigator {
                    Navigator(screen = RootScreen) { navigator ->
                        RootNavigator.setNavigator(navigator)
                        CurrentScreen()
                    }
                }
            }
        }
    }
}

/**
 * Default Navigator example
 * MBM_ComposeNavigation_DemoTheme {
 *     BottomSheetNavigator {
 *         Navigator(screen = RootScreen)
 *     }
 */