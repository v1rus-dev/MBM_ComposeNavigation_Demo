package yegor.cheprasov.mbm_decompose.decompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

interface PageComponent {
    val type: PageType
}

enum class PageType(
    val icon: ImageVector,
    val title: String
) {
    AllNotes(
        Icons.Filled.Home,
        "Все заметки"
    ),
    FavoriteNotes(
        Icons.Filled.Star,
        "Избранные заметки"
    )
}