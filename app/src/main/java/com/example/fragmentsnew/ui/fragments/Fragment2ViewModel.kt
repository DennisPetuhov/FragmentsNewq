package com.example.fragmentsnew.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentsnew.data.FragmentsNewRepositoryImpL
import com.example.fragmentsnew.data.room.NoteEntity
import kotlinx.coroutines.launch

class Fragment2ViewModel(
    private val repo: FragmentsNewRepositoryImpL
) : ViewModel() {
    init {
        Log.e("%%%", "viewmodel fragment 2 created")
    }


    val state: LiveData<List<NoteEntity>> get() = stateLiveData // каждый раз когда будет изменяться stateLiveData
    // , гетер будет присваивать это значение state когда много логики и другой тип во вьюмодели
    private val stateLiveData = MutableLiveData<List<NoteEntity>>()

    //val dbList = repo.getAllNotes()// в данном случае приходит livedata

    fun dbList(): LiveData<List<NoteEntity>>{
        return repo.getAllNotes()}


    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch { repo.deleleteNote(note) }
    }

    fun searchNote(text:String?): LiveData<List<NoteEntity>>{
       return repo.getNoteBySearch(text)

    }
}


