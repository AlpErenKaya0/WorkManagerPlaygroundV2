package com.example.workmanagerplayground

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CounterService: Service() {
private val counter = Counter()
    override fun onBind(p0: Intent?): IBinder? {
    return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action){
            CounterAction.START.name-> start()
            CounterAction.STOP.name-> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun notification(counterValue:Int){
        val counterNotification = NotificationCompat
            .Builder(this,"counter_channel")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Counter")
            .setContentText(counterValue.toString())
            .setStyle(NotificationCompat.BigTextStyle())
            .build()
        startForeground(1,counterNotification)
    }

    private fun start(){
        CoroutineScope(Dispatchers.Default).launch{
            counter.start().collect{counterValue ->
                Log.d("CounterService",counterValue.toString())
                notification(counterValue)
            }
        }
    }
    private fun stop(){
        counter.stop()
        stopSelf()
    }
    enum class CounterAction{
        START,
        RESUME,
        RESTART,
        PAUSE,
        STOP
    }


}