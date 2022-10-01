package com.example.fragmentsnew.ui.fragments


import android.content.DialogInterface
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener

import com.example.fragmentsnew.ui.dialogFragments.DialogFragment1
import com.example.fragmentsnew.R
import com.example.fragmentsnew.ui.dialogFragments.SingleChoiceDialogFragment

import com.example.fragmentsnew.databinding.FirstFragmentLayoutBinding



import org.koin.androidx.viewmodel.ext.android.viewModel

import kotlin.properties.Delegates.notNull

//private const val EDIT_TEXT_KEY = ""
//const val app_preferences ="app_preferences"


class Fragment1 : Fragment() {


    private var volume by notNull<Int>()
    private val vm by viewModel<FragmentOneViewModel>()
    private lateinit var binding: FirstFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentLayoutBinding.inflate(inflater, container, false)
        val bundle=Bundle().also { it.putString(START_KEY,"Start") }
        val intent =Intent()//намерение в аперационную систему
        intent.putExtra(START_KEY,bundle)
        intent.action=(Intent.ACTION_GTALK_SERVICE_CONNECTED)
        ContextCompat.startForegroundService(requireContext(),intent)

        initialazeView()
        fromButtonToSharedPreferences()
        binding.buttonSingleChoice.setOnClickListener{showSimpleDialogFragment()}
        binding.buttonFragment.setOnClickListener{showSingleChoiceDialogFragment()}
        volume=savedInstanceState?.getInt(KEY_VOLUME)?:50



        simpleDialogFragmentListener()
        setupSingleChoiceDialogFragmentListener()

        return binding.root // проговорить еще раз или прочитать - чего в самом низу
    }
    private fun fromButtonToSharedPreferences() {
        binding.buttonShmatton.setOnClickListener {
            Log.d(TAG, "FROMBUTTOMTOSHAREDPRAFERENCES()RUNNING")
            val txt = binding.editText.text.toString()
            binding.textView.text= txt
            val email = binding.email.text.toString()
            vm.saveToSharedPreferences(txt)
        vm.onButtonpresedToDBinViewModel(txt,email)} }

    private fun initialazeView() {
        Log.d(TAG, "initialazeView()RUNNING")
        binding.textView.text = vm.fromSharedPreferencesToTextView()

    }

    private fun simpleDialogFragmentListener() {
        parentFragmentManager.setFragmentResultListener(
            DialogFragment1.REQUEST_KEY, viewLifecycleOwner,
            FragmentResultListener { _, result ->
                val which = result.getInt(DialogFragment1.KEY_RESPONSE)
                when (which) {
                    DialogInterface.BUTTON_NEGATIVE -> showToast("Negative")
                    DialogInterface.BUTTON_NEUTRAL -> showToast("Neutral")
                    DialogInterface.BUTTON_POSITIVE -> showToast("Positive")
                }
            })
    }


    private fun showSimpleDialogFragment() {
        val dialogFragment1 = DialogFragment1() // инициализируем диалдог
        dialogFragment1.show(parentFragmentManager, DialogFragment1.TAG) // вызываем метод шоу this
        // for activity parentfrtagmentmanager for fragment, выхываем  ТАГ


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //preferencesFragment1 = requireContext().getSharedPreferences(app_preferences,MODE_PRIVATE) //создаем файл превернесис
        //  preferencesFragment1Editor = preferencesFragment1.edit()
        // initView()
//        repo = (requireActivity() as ActivityMain).repo // не понятен синтакисис
        //  val dao = ( requireContext() as App) <- контекс как АПП. получаем доступ к объекту апп и управлением всем его жизненным циклом
        //  далеевызываем метод->   .getNoteDao()//для обучения
        //   val dao = ( requireContext() as App) .getNoteDao().findByName("fff")//для обучения
        // val dao2 = requireContext().getDao() // экстэншн методы удобны для раширения функцианла имеющегося класса.
        // тут мы не иммем доступа к АПП


    }

    /* private fun onButtonPressed() {
         val id = UUID.randomUUID().toString()
         val txt = edit_text.text.toString()
         val email = email.text.toString()
         lifecycleScope.launchWhenStarted {
             Log.d(TAG, "!!!!!!!")
             repo.insertNote(NoteEntity(id, "", email, 1455L, txt))
         }


         repo.getAllNotes().observe(viewLifecycleOwner) { it }

     }

     */


    private fun showSingleChoiceDialogFragment() {
        SingleChoiceDialogFragment.show(parentFragmentManager, volume)
    }

    private fun setupSingleChoiceDialogFragmentListener() {
        SingleChoiceDialogFragment.setUpListener(parentFragmentManager, this) {
            this.volume = it
            updateUi()
        }
    }


    fun showToast(text: String) {
        return Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()


    }

    private fun updateUi() {
        binding.currentVolumeTextView.text = getString(R.string.current_volume, volume)
        // binding.colorView.setBackgroundColor(color)
    }







    /* private fun initView() {
         with(binding) {
             textView.text = preferencesFragment1.getString(EDIT_TEXT_KEY, "")

             buttonShmatton.setOnClickListener {
                 textView.text = editText.text
                 preferencesFragment1Editor.putString(
                     EDIT_TEXT_KEY,
                     binding.editText.text.toString()
                 ).apply()
             }
         }
     } // это без вью модели

     */


    companion object {
        @JvmStatic
        val TAG = Fragment1::class.java.simpleName

        @JvmStatic
        private val KEY_VOLUME = "KEY_VOLUME"

        @JvmStatic
        private val KEY_COLOR = "KEY_COLOR"
    }

}