package yegor.cheprasov.mbm_design.di

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import yegor.cheprasov.mbm_design.di.utils.DialogType

@Composable
fun AppDialog(
    dialogType: DialogType,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        when (dialogType) {
            is DialogType.DeleteNote -> DeleteNote(
                text = dialogType.title,
                onDismissRequest = onDismissRequest,
                onSuccess = { dialogType.onSuccess.invoke() }
            )
        }
    }
}

@Composable
private fun DeleteNote(text: String, onDismissRequest: () -> Unit, onSuccess: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 2.dp)
        ) {
            Text(
                text = "Внимание",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                text = "Вы действительно хотите удалить \"$text\"?",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 14.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onSuccess) {
                    Text(text = "Success")
                }
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Dismiss")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDeleteNote() {
    DeleteNote(text = "Привет", onDismissRequest = {}, onSuccess = {})
}