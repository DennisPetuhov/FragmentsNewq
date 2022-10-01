package com.example.fragmentsnew.ui

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.fragmentsnew.data.room.NoteDAO
import com.example.fragmentsnew.data.room.NoteDataBase
import com.example.fragmentsnew.data.room.UpdateUserNameTuple
import com.example.fragmentsnew.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application() {// для чего делали класс повторить
   // private lateinit var db:NoteDataBase
    override fun onCreate() {
        super.onCreate()
      //  db=NoteDataBase.getNoteDataBase(applicationContext) // создаем ноутдатабейс
       // db.getNoteDAO().findByName("jjj")?// доступ к методам

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule))
        }



    /*fun getNoteDao():NoteDAO{ // создаем метод для обращения к интерфесу дао
        Log.d(TAG, "**********getNoteDAO")
       return db.getNoteDAO()

    }

     */
  //  val a = getNoteDao().updateUsername(UpdateUserNameTuple("ddsd","fsdf"))
}



  /*  fun Context.getDao():NoteDAO { //экстеншн функция пишми где вызывается, название функции
    return (this as App).getNoteDao()



}

   */


}