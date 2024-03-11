package yegor.cheprasov.mbm_voyager.screenModels

import android.util.Log
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

class AllNotesScreenModel(
    private val notesRepository: NotesRepository
) : BaseScreenModel() {

    init {
        Log.d("myTag", "init all notes screen mod")
    }

}