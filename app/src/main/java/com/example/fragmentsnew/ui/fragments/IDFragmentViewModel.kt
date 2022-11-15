package com.example.fragmentsnew.ui.fragments

import androidx.lifecycle.ViewModel

import com.example.fragmentsnew.data.room.FragmentsNewRepositoryImpL


class IDFragmentViewModel(
    private val repo: FragmentsNewRepositoryImpL,
    private val id: String
) : ViewModel() {

    val noteLiveData = repo.getNoteById(id)
        //  lateinit var  name: Pair<a,b>




















    /*fun showNote(note: NoteEntity): NoteEntity {

            id?.let { viewModelScope.launch {
                repo.getNoteById(id) }
        }


    }

     */

}