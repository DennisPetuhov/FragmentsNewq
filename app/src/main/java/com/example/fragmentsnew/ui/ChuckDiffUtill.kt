package com.example.fragmentsnew.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.fragmentsnew.data.retrofit.chuckList.JokesRandomMulti

class ChuckDiffUtill(private val oldList: List<JokesRandomMulti>, private val newList: List<JokesRandomMulti>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}