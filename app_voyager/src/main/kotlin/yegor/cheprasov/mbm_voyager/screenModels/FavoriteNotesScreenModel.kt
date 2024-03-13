package yegor.cheprasov.mbm_voyager.screenModels

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_database.entities.Note
import yegor.cheprasov.mbm_voyager.utils.BaseScreenModel

class FavoriteNotesScreenModel(
    private val notesRepository: NotesRepository
) : BaseScreenModel() {

    private val mutableState = MutableStateFlow(State())

    val state: StateFlow<State> = mutableState

    init {
        observeFavoriteNotes()
    }

    private fun observeFavoriteNotes() = scope.launch {
        notesRepository
            .observeNotes()
            .collectLatest { list ->
                mutableState.update { it.copy(listOfNotes =  list) }
            }
    }

    data class State(
        val listOfNotes: List<Note> = emptyList()
    )

}