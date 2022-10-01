package com.example.fragmentsnew.ui.fragments

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.fragmentsnew.data.FragmentsNewRepositoryImpL
import com.example.fragmentsnew.data.retrofit.chuckIO.ChuckSingleApiServiceImp
import com.example.fragmentsnew.data.retrofit.chuckIO.JokesDataClass
import com.example.fragmentsnew.data.retrofit.chuckList.ChuckListServiceImpl
import com.example.fragmentsnew.data.retrofit.chuckList.JokeGik
import com.example.fragmentsnew.data.retrofit.chuckList.JokesListDataClass
import com.example.fragmentsnew.data.retrofit.chuckList.JokesRandomMulti
import com.example.fragmentsnew.data.room.NoteEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class Fragment3ViewModel(
    private val apiChuckSingleServiceImpl: ChuckSingleApiServiceImp,
    private val apiChuckListServiceImpl: ChuckListServiceImpl,
    private val repo:FragmentsNewRepositoryImpL
) : ViewModel() {
    init {
        Log.e("%%%", "Viewmodel fragment 3 created")

    }

    val state: StateFlow<JokesDataClass?> get() = privateState

    //StateFlow заменитель лайвдаты во вьюмодели и унего есть начальное состояние его можно вне корутины
    private val privateState = MutableStateFlow<JokesDataClass?>(null)


    val immutablelJokeMulti: StateFlow<List<JokesRandomMulti>?> get() = mutableJokeMulti

    private val mutableJokeMulti = MutableStateFlow<List<JokesRandomMulti>?>(null)


    val immutablelJokeOneOfMulti: StateFlow<JokesListDataClass?> get() = mutablelJokeOneOfMulti
    private val mutablelJokeOneOfMulti = MutableStateFlow<JokesListDataClass?>(null)


    val immutablelGik: StateFlow<JokeGik?> get() = mutablelGik
    private val mutablelGik = MutableStateFlow<JokeGik?>(null)


    fun gik() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Viewmodel getJokeOneOfMulti fun")
            try {
                val joke = apiChuckListServiceImpl.gik()
                mutablelGik.emit(joke)

            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e  // если отлавиливаем то корутина работает, отлоовили и бросили далее - остановили по эексепшену

                }

            }
        }
    }


    //  val state: LiveData<JokesDataClass> get() = privateState
    // private val privateState = MutableLiveData<JokesDataClass>()

    fun getJokes() {
        viewModelScope.launch(Dispatchers.IO) {// dispatchers IO (для input out потоков) для даныых указывать  потоки в корутине
            Log.d(TAG, "Viewmodel getJokes fun")
            try {
                val joke = apiChuckSingleServiceImpl.loadSingleJokeIo()
                privateState.emit(joke)

                val id = UUID.randomUUID().toString()
                repo.insertNote(NoteEntity(id,"",joke.value,1455L,""))

                Log.d(TAG, "EMIT IO")// emit like value in livedata
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e  // если отлавливаем CancellationException то корутина отменяется и
                    // мы перебрасываем и исключени не фильтруеться
                }
            }
        }

    }

    fun getJokeOneOfMulti() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Viewmodel getJokeOneOfMulti fun")
            try {
                val joke = apiChuckListServiceImpl.loadOneOfMultiRandomJokes()
                mutablelJokeOneOfMulti.emit(joke)
                Log.d(TAG, "EMIT")

            } catch (e: Exception) {
                e.printStackTrace()//пишим все ошибки
                if (e is CancellationException) {
                    throw e  // если отлавиливаем то корутина работает, отлоовили и бросили далее - остановили по эексепшену

                }
            }
        }
    }


     fun getJokesMulti(){
         viewModelScope.launch(Dispatchers.IO) {
             try{
                 val joke =apiChuckListServiceImpl.loadListOfRandomJokes()
                 mutableJokeMulti.emit(joke.value)
             } catch (e:Exception){
                 throw  e
             }
         }
     }
     fun random():Int{

         return nextInt(5,20)
     }


    /*  fun getJokes() {
          viewModelScope.launch(Dispatchers.IO) {// dispatchers IO (для input out потоков) для даныых указывать  потоки в корутине
              Log.d(TAG, "Viewmodel getJokes fun")
              try {
                  val joke = apiServiceImp.loadRandomJokes()
                  privateState.postValue(joke)  // когда из дрогого потока полльзуем лавдату -
                  // надо использовать постВэлью- когда есть веротяонсть что мы обращаемся к лайвдата из
                  // нескольких потоков. кокнуретно тут основной поток и потом для выхода в интернет

              } catch (e: Exception) {
                  if (e is CancellationException) {
                      throw e  // если отлавливаем CancellationException то корутина отменяется и
                  // мы перебрасываем и исключени не фильтруеться
                  }

              }



          }

      }
      */


}