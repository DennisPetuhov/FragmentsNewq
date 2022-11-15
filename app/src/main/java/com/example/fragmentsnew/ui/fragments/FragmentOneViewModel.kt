package com.example.fragmentsnew.ui.fragments

import android.content.ContentValues.TAG
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.fragmentsnew.data.room.FragmentsNewRepositoryImpL
import com.example.fragmentsnew.data.room.NoteEntity

import kotlinx.coroutines.launch
import java.util.*


private const val EDIT_TEXT_KEY = ""


class FragmentOneViewModel(
    private val repo: FragmentsNewRepositoryImpL,//добавляем зависимости в конструктор того что будем потом использовать во вьюмодели
    var preferences: SharedPreferences // добавили через коин
) : ViewModel() {

    init {
        Log.e("%%%", "viewmodel fragment 1 created")
    }



    fun saveToSharedPreferences(txt: String) {
        val preferencesEditor = preferences.edit()
        preferencesEditor.putString(EDIT_TEXT_KEY, txt).apply()

    }




    fun fromSharedPreferencesToTextView(): String? {

        return  preferences.getString(EDIT_TEXT_KEY, "")


    }

    fun onButtonpresedToDBinViewModel(editText: String, email: String) {
        val id = UUID.randomUUID().toString()
        viewModelScope.launch { repo.insertNote(NoteEntity(id, "", email, 1455L, editText)) }


    }

    fun observeAllNotes(): LiveData<List<NoteEntity>> {
        return repo.getAllNotes()
    }


   private fun onButtonPressedToDB(txt:String,email:String) {
        val id = UUID.randomUUID().toString()

        viewModelScope.launch {
            Log.d(TAG, "!!!!!!!")
            repo.insertNote(NoteEntity(id, "", email, 1455L, txt))
        }


       // repo.getAllNotes().observe(viewLifecycleOwner) {        }

    }




}