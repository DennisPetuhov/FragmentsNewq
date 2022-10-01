package com.example.fragmentsnew.data.retrofit.chuckIO

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ChuckNorrisApi {
    // апи  по аналогии как  дао класс в руме
   // @POST

  //  @GET("/jokes/random/{count}") // http запрос GET каунт вставляем колчиество шуток рандома
    @GET//("/jokes/random")
    suspend fun loadSingleJokeIo(
    @Url url:String="https://api.chucknorris.io/jokes/random"
      //  @Path("count")
     //   quantity:Int
    ): JokesDataClass       //Callback<Joke>




}
/*
***
* ***
* в корутине возвращется сам класс - сущность,
* в стандерте возвращается retrofit.callback
* в случаем кастомного адаптеора возвращает флоу или rxjava (2, 3)
 */