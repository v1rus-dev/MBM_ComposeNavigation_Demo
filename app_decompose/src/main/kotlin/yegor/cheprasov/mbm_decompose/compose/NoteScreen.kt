package yegor.cheprasov.mbm_decompose.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yegor.cheprasov.mbm_decompose.decompose.note.NoteComponent
import yegor.cheprasov.mbm_decompose.decompose.note.PreviewNoteComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteScreen(
    component: NoteComponent
) {
    Scaffold(
        topBar = {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { component.onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    BasicTextField2(
                        state = component.title,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        decorator = {
                            if (component.title.text.toString().isEmpty()) {
                                Text(
                                    text = "Заголовок",
                                    color = Color.Black.copy(0.5f),
                                    fontSize = 15.sp
                                )
                            } else {
                                it()
                            }
                        }
                    )
                }
                HorizontalDivider(color = Color.LightGray.copy(0.4f))
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            BasicTextField2(
                state = component.body,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }

}

@Preview
@Composable
fun PreviewNoteScreen() {
    NoteScreen(PreviewNoteComponent())
}