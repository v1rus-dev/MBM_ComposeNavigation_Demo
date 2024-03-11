package yegor.cheprasov.mbm_voyager.di

import org.koin.dsl.module
import yegor.cheprasov.mbm_voyager.screenModels.AllNotesScreenModel
import yegor.cheprasov.mbm_voyager.screenModels.FavoriteNotesScreenModel
import yegor.cheprasov.mbm_voyager.screenModels.NoteScreenModel

val screenModelsModule = module {
    factory { AllNotesScreenModel(notesRepository = get()) }
    factory { FavoriteNotesScreenModel(notesRepository = get()) }
    factory { NoteScreenModel(notesRepository = get()) }
}