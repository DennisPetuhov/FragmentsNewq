package com.example.fragmentsnew.data.room

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        NoteEntity::class
    ]
)
/*
***
*
* В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы.
*  Для каждого Entity класса из списка entities будет создана таблица.

В Database классе необходимо описать абстрактные методы для получения Dao объектов, которые вам понадобятся.
 */

abstract class  NoteDataBase : RoomDatabase() {
    // abstract fun noteDao():NoteDAO
    abstract fun getNoteDAO(): NoteDAO

/*
   companion object { //синглтон предотвращает создание многих сущностей БД
        @Volatile // объект всегда читается из основной памяти и н хранится в кэш процессора и тд читается из RAM
        private var INSTANCE: NoteDataBase? = null
        fun getNoteDataBase(context: Context): NoteDataBase {
            Log.d(ContentValues.TAG, "********** fun getNoteDataBase")
            return INSTANCE?: synchronized(this) { // работа с одним потоком одновременно
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "notedb"
                ).build()
                INSTANCE=instance
                instance
            }
        }


    }


 */

}