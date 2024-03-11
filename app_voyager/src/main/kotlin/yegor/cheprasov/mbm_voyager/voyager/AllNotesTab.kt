package yegor.cheprasov.mbm_voyager.voyager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import yegor.cheprasov.mbm_voyager.screenModels.AllNotesScreenModel
import yegor.cheprasov.mbm_voyager.utils.RootNavigator

object AllNotesTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Все заметки"
            val icon = rememberVectorPainter(image = Icons.Filled.Home)

            return remember {
                TabOptions(
                    index = 0u,
                    title,
                    icon
                )
            }
        }

    @Composable
    override fun Content() {

        val screenModel = RootNavigator.getRootNavigatorOrThrow().getNavigatorScreenModel<AllNotesScreenModel>()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { RootNavigator.getRootNavigatorOrThrow().push(NoteScreen()) }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

            }
        }
    }
}