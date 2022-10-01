package com.example.fragmentsnew.data.retrofit.chuckList


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ChuckListApi {




    @GET //("/jokes/random/{count}") // http запрос GET каунт вставляем колчиество шуток рандома

    suspend fun loadListOfRandomJokes(
        @Url url:String="http://api.icndb.com/jokes/random/10"
        // @Path("count")
        // quantity:Int
    ): JokesListDataClass


    @GET
    suspend fun loadOneOfMultiRandomJokes(
        @Url url:String="http://api.icndb.com/jokes/random/2"


    ): JokesListDataClass

    @GET// ("/api?format=json")
    suspend fun gik(
        @Url url:String ="https://geek-jokes.sameerkumar.website/api?format=json"
    ):JokeGik
}