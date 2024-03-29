package yegor.cheprasov.mbm_voyager.screenModels

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_database.entities.Note

class AllNotesScreenModel(
    private val notesRepository: NotesRepository
) : ScreenModel {

    private val mutableState = MutableStateFlow(State())

    val state: StateFlow<State> = mutableState

    init {
        observeNotes()
    }

    private fun observeNotes() = screenModelScope.launch {
        notesRepository
            .observeNotes()
            .collectLatest { list ->
                mutableState.update {
                    it.copy(list)
                }
            }
    }

    data class State(
        val listOfNotes: List<Note> = emptyList()
    )

}