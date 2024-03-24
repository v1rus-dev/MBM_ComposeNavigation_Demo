package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.stack.push
import yegor.cheprasov.mbm_decompose.decompose.RootConfig
import yegor.cheprasov.mbm_decompose.decompose.favoriteNotes.FavoriteNotesComponent
import yegor.cheprasov.mbm_decompose.decompose.favoriteNotes.PreviewFavoriteNotesComponent
import yegor.cheprasov.mbm_decompose.utils.RootNavigator
import yegor.cheprasov.mbm_design.di.NoteCardUI

@Composable
fun FavoriteNotesScreen(
    component: FavoriteNotesComponent
) {

    val state by component.state.collectAsState()

    val rootNavigator = RootNavigator.getNavigatorOrThrow()

    Scaffold {
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
                        rootNavigator.push(RootConfig.Note(it.uid, it.title))
                    }, onLongClick = {
                        component.showBottom()
                    })
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteNotesScreen() {
    FavoriteNotesScreen(
        component = PreviewFavoriteNotesComponent()
    )
}