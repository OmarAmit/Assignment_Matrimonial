package assignment.shaadi.ui

import android.app.Application
import assignment.shaadi.util.ConnectionMonitor

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ConnectionMonitor(this).startNetworkCallback()
    }
    override fun onTerminate() {
        super.onTerminate()
        ConnectionMonitor(this).stopNetworkCallback()
    }
}