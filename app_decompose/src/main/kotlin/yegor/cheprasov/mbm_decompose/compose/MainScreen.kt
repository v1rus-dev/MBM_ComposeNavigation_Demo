package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import yegor.cheprasov.mbm_decompose.decompose.PageType
import yegor.cheprasov.mbm_decompose.decompose.allNotes.AllNotesComponent
import yegor.cheprasov.mbm_decompose.decompose.favoriteNotes.FavoriteNotesComponent
import yegor.cheprasov.mbm_decompose.decompose.main.MainComponent

@Composable
fun MainScreen(
    component: MainComponent
) {

    val pages by component.pages.subscribeAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar {
                PageType.entries.forEach {
                    TabNavigationItem(pageType = it, activePageType = pages.active.instance.type) {
                        when (it) {
                            PageType.AllNotes -> component.onAllClicked()
                            PageType.FavoriteNotes -> component.onFavoriteClicked()
                        }
                    }
                }
            }
        }
    ) {
        Children(
            stack = pages,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) { child ->
            when (val instance = child.instance) {
                is AllNotesComponent -> AllNotesScreen(component = instance)
                is FavoriteNotesComponent -> FavoriteNotesScreen(component = instance)
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(
    pageType: PageType,
    activePageType: PageType,
    onClick: (PageType) -> Unit
) {
    NavigationBarItem(
        selected = pageType == activePageType,
        onClick = { onClick(pageType) },
        icon = {
            Icon(imageVector = pageType.icon, contentDescription = null)
        },
        label = {
            Text(text = pageType.title)
        }
    )
}