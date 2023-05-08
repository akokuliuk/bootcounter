package akokuliuk.bootcounter

import akokuliuk.bootcounter.bootcounter.BootCounterRepository
import akokuliuk.bootcounter.bootcounter.BootEventNotificationsWorker
import android.app.Application
import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class BootCounterApp : Application() {

    val bootCounterRepository by lazy {
        BootCounterRepository(
            this.getSharedPreferences("boot_counter_repository", Context.MODE_PRIVATE)
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        WorkManager.getInstance(this).enqueue(
            PeriodicWorkRequestBuilder<BootEventNotificationsWorker>(5, TimeUnit.SECONDS).build()
        )
    }

    companion object {
        lateinit var INSTANCE: BootCounterApp
            private set
    }
}