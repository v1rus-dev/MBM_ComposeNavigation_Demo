package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.BottomSheet
import yegor.cheprasov.mbm_decompose.decompose.bottomSheets.PreviewBottomSheet
import yegor.cheprasov.mbm_design.di.AppBottomSheet

@Composable
fun NoteBottomSheet(
    component: BottomSheet,
    modifier: Modifier = Modifier
) {
    AppBottomSheet(
        modifier = modifier,
        noteIsFavorite = component.noteIsFavorite,
        toggleFavorite = { component.toggleFavorite() },
        removeNote = { component.onRemoveNote() }
    )
}

@Preview
@Composable
fun PreviewNoteBottomSheet() {
    NoteBottomSheet(component = PreviewBottomSheet())
}