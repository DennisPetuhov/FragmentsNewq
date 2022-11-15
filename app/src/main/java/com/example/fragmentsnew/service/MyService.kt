package com.example.fragmentsnew.service/*



import android.app.Service
import android.content.Intent
import android.os.IBinder
class MyService : Service() {



    override fun onBind(p0: Intent?): IBinder? =null
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { if (it.hasExtra(START_KEY)){
            //что то делаем в зависимости  от пришедшей команды
        } }
            // создать натофикацию и предать в метод ниже ввместо с айди
        startForeground(1)
        return super.onStartCommand(intent, flags, startId)
    }

    companion object{

        val START_KEY="start"
        val

    }
}
*/
