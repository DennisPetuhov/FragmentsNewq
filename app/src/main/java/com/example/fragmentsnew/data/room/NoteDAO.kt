package com.example.fragmentsnew.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import java.nio.channels.SelectableChannel

/*
В DAO (объекте доступа к данным) вы указываете SQL-запросы и связываете их с вызовами методов. Компилятор проверяет SQL и
 создает запросы на основе удобных аннотаций для общих запросов, таких как @Insert. Room использует DAO для создания чистого
 API для вашего кода.

DAO должен быть интерфейсом или абстрактным классом.
 */

@Dao

interface NoteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNoteById(id: String): LiveData<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNameById(id: String): NoteEntity

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getEmailById(id: String): NoteEntity

    @Query("UPDATE note_table SET body=:body WHERE id=:id")
    suspend fun updateNoteNext(id: String, body: String)

    @Query("SELECT * from note_table")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Query("SELECT * from note_table WHERE time_stamp >:time")
    fun getNotesByQuery(time: Long): LiveData<List<NoteEntity>>

    @Query("SELECT * from note_table WHERE (:text  LIKE '%' || email LIKE :text) OR (:text LIKE '%'|| name LIKE :text ||'%') ")
    fun getNotesBySearch(text:String?): LiveData<List<NoteEntity>>

 //   @Query("SELECT * from note_table WHERE email LIKE :text OR name  LIKE  :text ")
  //  fun getNotesBySearch(text:String?): LiveData<List<NoteEntity>>

    @Query("SELECT email, id FROM note_table WHERE name = :name")

    suspend fun findByName(name: String): NameOfUserTuple?


   // @Query("SELECT email, name FROM note_table where id=:id ")
 //   suspend fun findById(id: String): EmailAndNameByIdTuple?


  //  @Query("SELECT email FROM note_table where id=:id ")
 //  fun findEmailById(id: String): LiveData<NoteEntity>





    @Update(entity = NoteEntity::class)
    suspend fun updateUsername(name: UpdateUserNameTuple)


}