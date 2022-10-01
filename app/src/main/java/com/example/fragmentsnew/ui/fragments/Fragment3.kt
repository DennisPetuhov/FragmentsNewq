package com.example.fragmentsnew.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
//import com.example.fragmentsnew.data.retrofit.chuckIO.JokesDataClass
import com.example.fragmentsnew.databinding.ThirdFragmentBinding
import com.example.fragmentsnew.ui.ChuckInternetAdapter


import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

import org.koin.androidx.viewmodel.ext.android.viewModel


class Fragment3 : Fragment() {

    private val viewModel by viewModel<Fragment3ViewModel>()
    private val adapter by inject<ChuckInternetAdapter>()

    private lateinit var binding: ThirdFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ThirdFragmentBinding.inflate(inflater, container, false)
        // binding.textFragment3.text = "${viewModel.state}"


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       // gik()

        buttonOneOfMulti()
         button()
        buttonMulti()
        initRecycler()
         subscribe()
         subscribeMulti()
         subscribeOneOfMulti()
        // subscribeLivedata()
        //subscribeGIK()


    }



    private fun initRecycler() {
        Log.d(TAG, "FRAGMENT 3 INITRECYCLER FUN")
      //  adapter.createAction { Toast.makeText(requireContext(),"CHUCK CHUCK CHUCK",Toast.LENGTH_SHORT).show() }
        binding.recycleView3Fragment.adapter = adapter
    }


    private fun subscribeGIK() {
        lifecycleScope.launchWhenStarted {
            Log.d(TAG, "FRAGMENT 3 SUBSCRIBE FUN")
            viewModel.immutablelGik.onEach { JokeGik ->
                JokeGik?.let {
                    binding.textMultiFragment3.text = JokeGik.joke
                }
            }.collect()
        }}



    private fun subscribeOneOfMulti() {
        lifecycleScope.launchWhenStarted {
            Log.d(TAG, "FRAGMENT 3 subscribeOneOfMulti FUN")
            viewModel.immutablelJokeOneOfMulti.onEach { JokesRandomMultiToOneJoke ->
                JokesRandomMultiToOneJoke?.let {
                    Log.d("@@@", it.type)
                    Log.d(TAG, "******************************************************")
                    binding.textMultiFragment3.text = JokesRandomMultiToOneJoke.value[0].joke
                }
            }.collect()

        }
    }



        private fun subscribe() {
            lifecycleScope.launchWhenStarted {
                Log.d(TAG, "FRAGMENT 3 SUBSCRIBE FUN")
                viewModel.state.onEach { JokeDataClassObject ->
                    JokeDataClassObject?.let {
                        binding.textFragment3.text = JokeDataClassObject.value
                    }
                }.collect()
            }
        }




    private fun subscribeMulti() {
        lifecycleScope.launchWhenStarted {
            Log.d(TAG, "FRAGMENT 3 SUBSCRIBE MULTI FUN")
            viewModel.immutablelJokeMulti.onEach { JokesRandomMulti ->
                JokesRandomMulti?.let {
                    adapter.setItemList(it)
                }
            }.collect()
        }
    }


        /* private fun subscribeLivedata() {
             viewModel.state.observe(viewLifecycleOwner) { JokesDataClassObject ->
                 binding.textFragment3.text = JokesDataClassObject.value
             }
             foo("viewLifecycleOwner") { JokesDataClassObject ->
                 binding.textFragment3.text = JokesDataClassObject.value
             }
         }

            private fun foo(owner: String, action: (JokesDataClass) -> Unit) {} // observe внутри

         */ // лайвдата


        private fun button() {
            binding.button3Fragment.setOnClickListener {
                Log.d(TAG, "BUTTON 3 PRESSED")
                //  viewModel.chuckIo()
                viewModel.getJokes()


            }

        }


    private fun gik() {
        binding.button3FragmentMulti.setOnClickListener {
            Log.d(TAG, "BUTTON GIK 3 PRESSED")
            //  viewModel.chuckIo()
            viewModel.gik()


        }

    }
    private fun buttonOneOfMulti() {
        binding.button3FragmentMulti.setOnClickListener {
            Log.d(TAG, "buttonOneOfMulti 3 PRESSED")
            //  viewModel.chuckIo()
            viewModel.getJokeOneOfMulti()


        }

    }

    private fun buttonMulti() {
        binding.button3FragmentRecycleView.setOnClickListener {
            Log.d(TAG, "BUTTON 3 RECYCLE PRESSED")
            // viewModel.chuckMulti()
            viewModel.getJokesMulti()

        }

    }



}