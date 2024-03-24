package yegor.cheprasov.mbm_decompose.instance

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_database.entities.Note
import yegor.cheprasov.mbm_decompose.utils.BaseInstance

class FavoriteNotesInstance : BaseInstance() {

    private val notesRepository: NotesRepository by inject()

    private val mutableState = MutableStateFlow(State())

    val state: StateFlow<State> = mutableState

    init {
        observeFavoriteNotes()
    }

    private fun observeFavoriteNotes() = scope.launch {
        notesRepository
            .observeFavoriteNotes()
            .collectLatest { list ->
                mutableState.update { it.copy(listOfNotes =  list) }
            }
    }


    data class State(
        val listOfNotes: List<Note> = emptyList()
    )

}