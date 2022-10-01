package com.example.fragmentsnew.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentsnew.data.room.NoteEntity

import com.example.fragmentsnew.databinding.SecondFragmentItemLayoutBinding

class NoteViewHolder(val binding: SecondFragmentItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    val deleteNote=binding.menuDelete
    val myPopUpMenu = binding.menuMore
    val myIdPressButton =binding.avatar




    fun onBind(item:NoteEntity){
        binding.apply {
            email.text=item.email
            title.text=item.name
        }
//В созданном классе-контейнере нужно просто перечислить используемые компоненты из макета для отдельного элемента списка.
    }
}