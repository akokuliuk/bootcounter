package akokuliuk.bootcounter.bootcounter

import akokuliuk.bootcounter.BootCounterApp
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

// TODO: Extract interface
class BootEventNotificationEmitter {
    private val bootCounterRepository by lazy {
        BootCounterApp.INSTANCE.bootCounterRepository
    }

    fun emitBootEventsNotification(cxt: Context) {
        val manager =
            cxt.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(manager)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(cxt, CHANNEL_ID)
                .setContentTitle("Last boot events")
                .setContentText(
                    "Last boot events: ${
                        bootCounterRepository.loadRecentBootEvents().joinToString(" and ")
                    }"
                )
                .setSmallIcon(R.mipmap.sym_def_app_icon)
        manager.notify(1, builder.build())
    }

    private fun createNotificationChannel(manager: NotificationManager): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager.notificationChannels.any { it.id == CHANNEL_ID }) {
                return null
            }

            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            manager.createNotificationChannel(channel)
            channel
        } else {
            null
        }
    }

    companion object {
        const val CHANNEL_ID = "boot_event_notification_channel"

        //TODO: Should be localised
        const val CHANNEL_NAME = "Boot Events"
    }
}