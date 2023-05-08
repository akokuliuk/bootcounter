package akokuliuk.bootcounter.bootcounter

import akokuliuk.bootcounter.BootCounterApp
import akokuliuk.bootcounter.utils.Timestamp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootCompletedBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        //TODO: Filter events by action here
        BootCounterApp.INSTANCE
            .bootCounterRepository
            .storeLastBootEvent(Timestamp(System.currentTimeMillis()))
    }
}
