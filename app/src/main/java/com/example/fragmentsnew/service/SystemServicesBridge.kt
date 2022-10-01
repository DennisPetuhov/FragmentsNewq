/*
package com.example.fragmentsnew.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.IBinder

class SystemServicesBridge(val context:Context): Service() {


    val gps = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager // мои локейшн менеджер

    @SuppressLint("MissingPermission")
    fun foo():Location?{
      val a=  gps.getLastKnownLocation(LocationManager.GPS_PROVIDER)
       // a.
                gps.getLastKnownLocation()
    }
    override fun onBind(p0: Intent?): IBinder? =null
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { if (it.hasExtra(START_KEY)){
            //что то делает от пришедшей команды
        } }
        return super.onStartCommand(intent, flags, startId)
    }

    companion object{

        val START_KEY="start"

    }
}
*/
