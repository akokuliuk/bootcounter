package akokuliuk.bootcounter

import akokuliuk.bootcounter.bootcounter.BootCounterRepository
import android.app.Application
import android.content.Context

class BootCounterApp : Application() {

    val bootCounterRepository by lazy {
        BootCounterRepository(
            this.getSharedPreferences("boot_counter_repository", Context.MODE_PRIVATE)
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: BootCounterApp
            private set
    }
}