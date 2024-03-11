package yegor.cheprasov.mbm_data.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import yegor.cheprasov.mbm_data.repositories.NotesRepository
import yegor.cheprasov.mbm_database.di.databaseModule

val dataModule = module {
    includes(databaseModule)

    single {
        NotesRepository(
            noteDao = get(),
            coroutineDispatcher = Dispatchers.IO
        )
    }
}