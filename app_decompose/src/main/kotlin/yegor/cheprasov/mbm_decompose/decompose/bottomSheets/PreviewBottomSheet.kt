package yegor.cheprasov.mbm_decompose.decompose.bottomSheets

class PreviewBottomSheet : BottomSheet {
    override val noteIsFavorite: Boolean = true

    override fun toggleFavorite() = Unit

    override fun onRemoveNote() = Unit
    override fun onHide() = Unit
}