package yegor.cheprasov.mbm_design.di.utils

sealed interface DialogType {

    data class DeleteNote(
        val noteUid: Int,
        val title: String,
        val onSuccess: () -> Unit
    ) : DialogType

}