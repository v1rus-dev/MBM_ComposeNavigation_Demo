package yegor.cheprasov.mbm_decompose.decompose.bottomSheets

interface BottomSheet {

    val noteIsFavorite: Boolean

    fun toggleFavorite()

    fun onRemoveNote()

    fun onHide()

}