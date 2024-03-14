package yegor.cheprasov.mbm_voyager.voyager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import org.koin.core.parameter.parametersOf
import yegor.cheprasov.mbm_design.di.AppBottomSheet
import yegor.cheprasov.mbm_design.di.AppDialog
import yegor.cheprasov.mbm_design.di.utils.DialogType
import yegor.cheprasov.mbm_voyager.screenModels.NoteBottomSheetScreenModel

data class NoteBottomSheet(val noteId: Int, val noteIsFavorite: Boolean) : Screen {
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val screenModel = getScreenModel<NoteBottomSheetScreenModel> { parametersOf(noteId) }

        var isShowRemoveDialogTitle by remember {
            mutableStateOf<String?>(null)
        }

        val onRemoveNote = remember<() -> Unit> {
            {
                screenModel.getNoteTitle {
                    isShowRemoveDialogTitle = it
                }
            }
        }

        if (isShowRemoveDialogTitle != null) {
            AppDialog(
                dialogType = DialogType.DeleteNote(
                    noteId,
                    isShowRemoveDialogTitle!!,
                    onSuccess = {
                        screenModel.removeNote {
                            bottomSheetNavigator.hide()
                        }
                    }
                )
            ) {
                isShowRemoveDialogTitle = null
            }
        }

        AppBottomSheet(
            noteIsFavorite = noteIsFavorite,
            toggleFavorite = {
                screenModel.toggleFavorite {
                    bottomSheetNavigator.hide()
                }
            },
            removeNote = onRemoveNote
        )
    }
}