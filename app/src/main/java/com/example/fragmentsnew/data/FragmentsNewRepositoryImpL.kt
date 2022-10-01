package com.example.fragmentsnew.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Query
import com.example.fragmentsnew.data.room.*

class FragmentsNewRepositoryImpL(private val dao: NoteDAO) {
    suspend fun insertNote(note: NoteEntity) {
        Log.d(TAG, "**********insertNote RepositoryIMPL")
        dao. insertNote(note)

    }
    fun getNoteBySearch(text:String?):LiveData<List<NoteEntity>>{
        return dao.getNotesBySearch(text)
    }

     fun getNoteById(id: String):LiveData<NoteEntity>{
       return dao.getNoteById(id)
    }
   suspend fun getNameById(id: String):NoteEntity{
       return dao.getNameById(id)
    }



    suspend fun deleleteNote(note:NoteEntity) {
        dao.deleteNote(note)
    }

    suspend fun updateNoteNext(id:String, body: String) {
        dao.updateNoteNext(id,body)



    }
    suspend fun findUserEmailByHisName(name:String){
        dao.findByName(name)

    }

  /*  suspend fun findUserEmailAndNameById (id: String){

        dao.findById(id)
    }
     fun findEmailById (id: String){

        dao.findEmailById(id)
    }


   */






    suspend fun updateUserName(id:String,name:String){
        dao.updateUsername(UpdateUserNameTuple(id,name))
    }
    /*
    * имлимиминитироватьб остальгные методы из дао
    * прочитать про иннерджони операции в эскюэллайт
     */

    fun getAllNotes(): LiveData <List<NoteEntity>> {
        Log.d(TAG, "**********getALLNotes RepositoryIMPL")
        return dao.getAllNotes()
    }

}