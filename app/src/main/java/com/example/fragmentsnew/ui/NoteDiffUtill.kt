package com.example.fragmentsnew.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.fragmentsnew.data.room.NoteEntity

class NoteDiffUtill(val oldList: List<NoteEntity>, val newList: List<NoteEntity>) : // новый приходит из фрагмента старый что был
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size


    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition] // == работает с датакласом как equal
    // в джаве если не датакласс - то надо переопределять equal либо сравнивать все по полям
}