package akokuliuk.bootcounter.bootcounter

import akokuliuk.bootcounter.utils.Timestamp
import android.content.SharedPreferences

//TODO: Extract interface
//TODO: Move to DI graph
class BootCounterRepository(
    private val prefs: SharedPreferences
) {
    fun storeLastBootEvent(timestamp: Timestamp) {
        val recentBootEvent = prefs.getLong(RECENT_BOOT_EVENT_KEY, 0)
        prefs.edit()
            .putLong(RECENT_BOOT_EVENT_KEY, timestamp.value)
            .putLong(BEFORE_RECENT_BOOT_EVENT_KEY, recentBootEvent)
            .apply()
    }

    /**
     * @return last boot events, from 0 to 2 values
     */
    //TODO: Tuple2 type here
    fun loadRecentBootEvents(): List<Timestamp> =
        listOf(
            Timestamp(prefs.getLong(RECENT_BOOT_EVENT_KEY, 0)),
            Timestamp(prefs.getLong(BEFORE_RECENT_BOOT_EVENT_KEY, 0))
        ).filter {
            it.value != 0L
        }

    companion object {
        const val RECENT_BOOT_EVENT_KEY = "recent_boot"
        const val BEFORE_RECENT_BOOT_EVENT_KEY = "before_recent_boot"
    }
}
