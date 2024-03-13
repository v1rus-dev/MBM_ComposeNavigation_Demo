package yegor.cheprasov.mbm_voyager.voyager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.core.parameter.parametersOf
import yegor.cheprasov.mbm_design.di.AppDialog
import yegor.cheprasov.mbm_design.di.utils.DialogType
import yegor.cheprasov.mbm_voyager.screenModels.NoteScreenModel

data class NoteScreen(
    val noteUid: Int? = null,
    val initTitle: String? = null
) : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val localNavigator = LocalNavigator.current

        val noteScreen = getScreenModel<NoteScreenModel> { parametersOf(noteUid, initTitle) }

        val onBack = remember {
            {
                noteScreen.save()
                if (localNavigator != null) {
                    localNavigator.pop()
                }
            }
        }

        var dialogIsOpen by remember {
            mutableStateOf<DialogType?>(null)
        }

        LaunchedEffect(key1 = noteScreen.dialog) {
            dialogIsOpen = noteScreen.dialog.value
        }


        Screen(
            title = noteScreen.title,
            body = noteScreen.body,
            dialogIsOpen = dialogIsOpen,
            dismissDialog = { noteScreen.clearDialog() },
            onBack = onBack
        )
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screen(
        title: TextFieldState,
        body: TextFieldState,
        dialogIsOpen: DialogType?,
        dismissDialog: () -> Unit,
        onBack: () -> Unit
    ) {
        if (dialogIsOpen != null) {
            AppDialog(dialogType = dialogIsOpen, onDismissRequest = dismissDialog)
        }

        Scaffold(
            topBar = {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { onBack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                        BasicTextField2(
                            state = title,
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            decorator = {
                                if (title.text.toString().isEmpty()) {
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
                    state = body,
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
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun PreviewContent() {
    val title = rememberTextFieldState()
    val body = rememberTextFieldState()

    var dialogIsOpen by remember {
        mutableStateOf<DialogType?>(null)
    }

    NoteScreen(1).Screen(
        title = title,
        body = body,
        dialogIsOpen = dialogIsOpen,
        dismissDialog = {
            dialogIsOpen = null
        },
        onBack = {
            dialogIsOpen = DialogType.DeleteNote(1, "Заголовок", {})
        })
}