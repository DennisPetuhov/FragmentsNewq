package com.example.fragmentsnew.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsnew.data.room.NoteEntity
import com.example.fragmentsnew.databinding.FirstFragmentLayoutBinding
import com.example.fragmentsnew.databinding.IdFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class IDFragment : Fragment() {
    private var id: String? = null
    private val viewModelIdFragment by viewModel<IDFragmentViewModel> { parametersOf(id) }

    // переадем во вью модель параметр ID из вне  через {paramatersOf(id)} сначала во фрагменте потом в DI потом во фрагмент
    private lateinit var binding: IdFragmentBinding


    override fun onAttach(context: Context) { //для приема аргументов используем этот метод
        super.onAttach(context)
        val args = requireArguments().getString(Fragment2.ARGS_KEY) // получили аргумент из бандла
        if (args == null) {
            throw IllegalArgumentException(" Provide ID please")
        } else {
            id = args // передали аргумент
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = IdFragmentBinding.inflate(inflater, container, false)



        subscribe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // для отрисвооки экрана
        super.onViewCreated(view, savedInstanceState)


    }

    private fun subscribe() {
        viewModelIdFragment.noteLiveData.observe(viewLifecycleOwner) {
            binding.apply {// тоже самое что with
                fourthFragmentEmail.text = it.name
                fourthFragmentName.text = it.email
            }

        }

    }
}