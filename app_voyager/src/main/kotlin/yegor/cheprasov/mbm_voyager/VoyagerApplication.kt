package yegor.cheprasov.mbm_voyager

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import yegor.cheprasov.mbm_data.di.dataModule
import yegor.cheprasov.mbm_voyager.di.screenModelsModule

class VoyagerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VoyagerApplication)
            modules(dataModule, screenModelsModule)
        }
    }

}