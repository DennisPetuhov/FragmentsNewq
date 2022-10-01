package com.example.fragmentsnew.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentsnew.data.retrofit.chuckList.JokesListDataClass
import com.example.fragmentsnew.data.retrofit.chuckList.JokesRandomMulti
import com.example.fragmentsnew.databinding.ThirdFragmentItemLayoutBinding

class ChuckViewHolder(val binding:ThirdFragmentItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
    val chuckJokeField = binding.jokeText



    fun onBindChuck(item:JokesRandomMulti){
        binding.jokeText.text=item.joke
    }
}