package yegor.cheprasov.mbm_voyager.voyager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import yegor.cheprasov.mbm_design.di.NoteCardUI
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

        val rootNavigator = RootNavigator.getRootNavigatorOrThrow()

        val screenModel = rootNavigator.getNavigatorScreenModel<AllNotesScreenModel>()

        val state by screenModel.state.collectAsState()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    RootNavigator.getRootNavigatorOrThrow().push(NoteScreen())
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(items = state.listOfNotes, key = { it.uid }) {
                        NoteCardUI(title = it.title, desc = it.body, onClick = {
                            rootNavigator.push(NoteScreen(it.uid, it.title))
                        })
                    }
                }
            }
        }
    }
}