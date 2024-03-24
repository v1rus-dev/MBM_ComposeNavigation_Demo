package yegor.cheprasov.mbm_decompose.instance.di

import org.koin.dsl.module
import yegor.cheprasov.mbm_decompose.instance.NoteInstance

val instanceModule = module {

    factory {
        NoteInstance(
            noteUid = it.get(0),
            initTitle = it.get(1),
            notesRepository = get()
        )
    }

}