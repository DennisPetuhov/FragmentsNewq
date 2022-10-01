package com.example.fragmentsnew.data.retrofit.chuckList

import android.content.ContentValues
import android.util.Log

class ChuckListServiceImpl(private val api: ChuckListApi) {
    suspend fun loadListOfRandomJokes(): JokesListDataClass =
        api.loadListOfRandomJokes()

    suspend fun loadOneOfMultiRandomJokes(): JokesListDataClass {
        Log.d(ContentValues.TAG, "loadOneOfMultiRandomJokes fun SERVICE IMPL")
        return api.loadOneOfMultiRandomJokes()
    }

    suspend fun gik(): JokeGik = api.gik()


}