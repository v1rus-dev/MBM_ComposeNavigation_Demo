package yegor.cheprasov.mbm_database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import yegor.cheprasov.mbm_database.dao.NoteDao
import yegor.cheprasov.mbm_database.room.AppDatabase

val databaseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "mbm_database"
        ).build()
    }

    single<NoteDao> {
        get<AppDatabase>().noteDao()
    }
}