package yegor.cheprasov.mbm_voyager.screenModels

import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

class NoteScreenModel(
    private val notesRepository: NotesRepository
) : BaseScreenModel() {

}