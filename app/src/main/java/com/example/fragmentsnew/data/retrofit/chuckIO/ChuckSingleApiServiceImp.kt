package com.example.fragmentsnew.data.retrofit.chuckIO

class ChuckSingleApiServiceImp(private val api: ChuckNorrisApi)//передаем апи в конструктор


// аписервис это как репозиторий
 {
    suspend fun loadSingleJokeIo(): JokesDataClass = api.loadSingleJokeIo()
}
