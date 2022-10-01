package com.example.fragmentsnew.di

import android.content.Context
import androidx.room.Room
import com.example.fragmentsnew.data.FragmentsNewRepositoryImpL
import com.example.fragmentsnew.data.retrofit.chuckIO.ChuckNorrisApi
import com.example.fragmentsnew.data.retrofit.chuckIO.ChuckSingleApiServiceImp
/*import com.example.fragmentsnew.data.retrofit.chuckIO.ChuckNorrisApi
import com.example.fragmentsnew.data.retrofit.chuckIO.ChuckSingleApiServiceImp*/
import com.example.fragmentsnew.data.retrofit.chuckList.ChuckListApi
import com.example.fragmentsnew.data.retrofit.chuckList.ChuckListServiceImpl
import com.example.fragmentsnew.data.room.NoteDAO
import com.example.fragmentsnew.data.room.NoteDataBase
import com.example.fragmentsnew.ui.ChuckInternetAdapter
import com.example.fragmentsnew.ui.NoteAdapter
import com.example.fragmentsnew.ui.fragments.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val app_preferences = "app_preferences"

val appModule = module {

    // factory { }  всегда новый обьект класса
    // single {  }  всегда тот же обьект класса



    single<Retrofit> {
        Retrofit                       // создаем наш ретрофит как датабейс      нет зависимостей
            .Builder()//паттерн билдер
            // .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))// адаптер между моши и реторифт чтбы ретрофит мог использовать моши для парсинга джсон ответов и формирования запрос в джсон формате
            .baseUrl("https://api.github.com")
            // .addCallAdapterFactory(FlowCallAdapterFactory.create())// addCallAdapterFactory адаптер по заверщении загрузки будет передавть данные мне
            //  .addConverterFactory(GsonConverterFactory.create())// GsonConverterFactory - конкретный  конвертер фэктори гугул. addConverterFactory превращает  джсон в наш объект
            .build()                 // создаем объект ретрофита
    }
    single<Moshi> { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() } // создаем прсер моши
    single<ChuckNorrisApi> { get<Retrofit>().create(ChuckNorrisApi::class.java) } // создаем апишку (аналогия рум - как  дао класс)  требуется ретрофит
    // переадем объект в виде самого себя в виде класса. метакласс который создаем другие клаассы  называется сlass
    // возвращет всеравно как интерфейс


    single { ChuckSingleApiServiceImp(get()) }       // создаем сервис (по аналогии с рум - репозиторий) который get апишку
    // требуется апишка
    // сервис передаем во вьюмодель и дальше сним взаимодействуем

    single<ChuckListApi> { get<Retrofit>().create(ChuckListApi::class.java) }
    single { ChuckListServiceImpl(get()) }



    viewModel { Fragment3ViewModel(get(), get(),get()) }


    viewModel { Fragment2ViewModel(get()) }

    viewModel { FragmentOneViewModel(get(), get()) }
    viewModel { params -> //принимает параметр который передаввали во вьюмодели
        IDFragmentViewModel(get(), params.get())
    }
    viewModel { FragmentFourViewModel() }

    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            NoteDataBase::class.java,
            "notedb"
        ).build()
    }
    single<NoteDAO> {
        get<NoteDataBase>().getNoteDAO()
    }
    single {
        androidContext().getSharedPreferences(
            app_preferences,
            Context.MODE_PRIVATE
        ) // из контекста вызывваемм метод
        // создания фаqйла с именем app_preferences и укахываем обязтельно коснтану MODE_PRIVATE
    }

    single { FragmentsNewRepositoryImpL(get()) }

    factory {
        NoteAdapter()
    }
    factory {
        ChuckInternetAdapter()
    }

}

