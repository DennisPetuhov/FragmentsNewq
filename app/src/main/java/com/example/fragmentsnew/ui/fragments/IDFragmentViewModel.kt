package com.example.fragmentsnew.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.fragmentsnew.data.FragmentsNewRepositoryImpL
import kotlinx.coroutines.launch


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