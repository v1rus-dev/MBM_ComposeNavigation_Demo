package yegor.cheprasov.mbm_voyager.voyager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object FavoriteNotesTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Избранные заметки"
            val icon = rememberVectorPainter(image = Icons.Filled.Star)

            return remember {
                TabOptions(
                    index = 1u,
                    title,
                    icon
                )
            }
        }

    @Composable
    override fun Content() {
        Scaffold {
            Column(modifier = Modifier.fillMaxSize().padding(it)) {

            }
        }
    }
}