package yegor.cheprasov.mbm_decompose.decompose.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
class PreviewNoteComponent : NoteComponent {
    override fun onBack() = Unit

    override val title: TextFieldState = TextFieldState()
    override val body: TextFieldState = TextFieldState()
}