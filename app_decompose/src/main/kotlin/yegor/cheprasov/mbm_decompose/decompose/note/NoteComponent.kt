package yegor.cheprasov.mbm_decompose.decompose.note

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState

@OptIn(ExperimentalFoundationApi::class)
interface NoteComponent {

    fun onBack()

    val title: TextFieldState
    val body: TextFieldState

}