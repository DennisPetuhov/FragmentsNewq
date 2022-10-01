package com.example.fragmentsnew

import com.example.fragmentsnew.data.room.NoteEntity

interface ItemActions {



    fun onClick(id:String)
    fun onLongClick(email:String)

   fun onMenuDeleteDeleteNote(note:NoteEntity)
   fun onIdToFragmentId(note:NoteEntity)
}