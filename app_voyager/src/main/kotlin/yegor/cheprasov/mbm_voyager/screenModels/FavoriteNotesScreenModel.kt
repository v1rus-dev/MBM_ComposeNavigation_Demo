package yegor.cheprasov.mbm_voyager.screenModels

import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

class FavoriteNotesScreenModel(
    private val notesRepository: NotesRepository
) : BaseScreenModel() {
}