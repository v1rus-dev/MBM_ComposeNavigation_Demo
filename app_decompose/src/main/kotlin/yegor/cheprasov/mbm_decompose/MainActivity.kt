package yegor.cheprasov.mbm_decompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.arkivanov.decompose.defaultComponentContext
import yegor.cheprasov.mbm_decompose.compose.RootScreen
import yegor.cheprasov.mbm_decompose.decompose.RootComponent
import yegor.cheprasov.mbm_decompose.ui.theme.MBM_ComposeNavigation_DemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponent = RootComponent(componentContext = defaultComponentContext())

        setContent {
            MBM_ComposeNavigation_DemoTheme {
                Surface {
                    RootScreen(component = rootComponent)
                }
            }
        }
    }
}