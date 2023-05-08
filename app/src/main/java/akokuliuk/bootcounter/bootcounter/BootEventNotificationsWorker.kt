package akokuliuk.bootcounter.bootcounter

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


class BootEventNotificationsWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        BootEventNotificationEmitter().emitBootEventsNotification(applicationContext)
        return Result.success()
    }
}
