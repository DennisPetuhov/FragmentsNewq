package com.example.fragmentsnew.data.room

import androidx.room.ColumnInfo

data class NameOfUserTuple( // чтоб достать только емейл  и айди по имени
    val id:String,
    val email: String
)

data class  UpdateUserNameTuple(// чтоб обновить только одно поле в таблийе приперезаписи
    val id:String,
    val name: String
)

data class  EmailAndNameByIdTuple(// чтоб достать  емейл и имя по айди
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    val name: String
)