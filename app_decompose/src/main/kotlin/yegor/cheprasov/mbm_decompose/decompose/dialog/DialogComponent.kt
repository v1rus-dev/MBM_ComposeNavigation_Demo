package yegor.cheprasov.mbm_decompose.decompose.dialog

import yegor.cheprasov.mbm_design.di.utils.DialogType

sealed interface DialogComponent {

    val dialogType: DialogType

    fun onSuccess()

    fun onDismiss()

}