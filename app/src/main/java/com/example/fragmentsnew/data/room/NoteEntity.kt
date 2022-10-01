package com.example.fragmentsnew.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.fragmentsnew.Note

@Entity(
    tableName = "note_table",
    indices = [Index("email", unique = true)

    ]
) //Annotated class that describes a database table when working with Room.
data class NoteEntity(
    @PrimaryKey
    val id: String,

    val body: String,
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    val name:String
) {
    fun NoteEntityToNote(): Note = Note(
        id = id,
        body = body,
        email = email,
        createdAT = timeStamp,
        name=name
    )

}