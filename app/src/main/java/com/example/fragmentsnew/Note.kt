package com.example.fragmentsnew

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Note(
    val id: String,
    val body: String,
    val email: String,
    val name: String,
    val createdAT: Long = UNKNOWN_CREATED_AT

) {
    companion object {
        const val UNKNOWN_CREATED_AT = 0L
    }
}