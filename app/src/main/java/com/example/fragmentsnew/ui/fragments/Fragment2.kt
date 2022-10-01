package com.example.fragmentsnew.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.navigation.fragment.findNavController
import com.example.fragmentsnew.ItemActions
import com.example.fragmentsnew.R
import com.example.fragmentsnew.data.room.NoteEntity
import com.example.fragmentsnew.databinding.SecondFragmentLayoutBinding
import com.example.fragmentsnew.ui.NoteAdapter

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class Fragment2 : Fragment() {

    companion object {
        const val ARGS_KEY = "ARGS_KEY"
    }

    private lateinit var binding: SecondFragmentLayoutBinding
    private val adapter by inject<NoteAdapter>()
    private val viewModel by viewModel<Fragment2ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        //  Log.d(ContentValues.TAG, "Fregment2*****onCreateView")
        binding = SecondFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "ActivityFragment2 Created")



        search()


        initRecycler()

        // layout()
        //  viewModel.state.observe(viewLifecycleOwner){}

        subscribeOnData()


    }

    private fun search() {
        var mySearch = binding.searchBar
        if (mySearch != null) {
            val searchView = mySearch as SearchView// до этого предлагало реализовать 4 метода вместо двух
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText!!.isNotEmpty()) {
                        Log.d(TAG, "onQueryTextChange IF")
                         val searchText = "%$newText%"
                        viewModel.searchNote(searchText)
                            .observe(viewLifecycleOwner) { adapter.setItemList(it) }


                    } else {
                        Log.d(
                            TAG,
                            "onQueryTextChange ELSE*********************************************************"
                        )
                        viewModel.dbList().observe(viewLifecycleOwner) {

                            adapter.setItemList(it)


                        }
                    }
                    return true

                }
            })
        }
    }


    private fun subscribeOnData() {

        viewModel.dbList().observe(viewLifecycleOwner) {

            adapter.setItemList(it)
            //  adapter.notifyDataSetChanged()  для обновления но работает не оч красиво визуально

            //    Log.d("@@@", it.size.toString())
            // binding.recycle.adapter!!.notifyDataSetChanged()

        }
    }
    //  private fun renderState(state:Fragment2ViewModel.State) =  adapter.setItemList(state.listNoteEntityViewModel)


    private fun initRecycler() {
        Log.d("@@@", "fragment2INITrecicler")




        adapter.bindaction(object : ItemActions {


            override fun onClick(id: String) {
                Toast.makeText(requireContext(), "gggg", Toast.LENGTH_SHORT).show()
            }

            override fun onLongClick(email: String) {
                Toast.makeText(requireContext(), email, Toast.LENGTH_SHORT).show()
            }

            override fun onMenuDeleteDeleteNote(note: NoteEntity) {
                viewModel.deleteNote(note)
            }

            override fun onIdToFragmentId(note: NoteEntity) {
                // val controller = findNavController(R.id.my_container)
                findNavController().navigate(R.id.action_fragment2_to_IDFragment, Bundle().also {
                    it.putString(
                        ARGS_KEY, note.id
                    )
                })


            }


        })
        binding.recycle.adapter = adapter // передаем реализацию адаптера для ресайклвью
    }

    /*fun showToast(text: String) {
        return Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()


    }

     */

}