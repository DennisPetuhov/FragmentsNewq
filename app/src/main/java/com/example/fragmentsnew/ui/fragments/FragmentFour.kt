package com.example.fragmentsnew.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsnew.ui.dialogFragments.MultiDialogFragment
import com.example.fragmentsnew.databinding.FourthFragmentLayoutBinding
import kotlin.properties.Delegates.notNull

class FragmentFour : Fragment() {
    private var color by notNull<Int>()
    lateinit var binding: FourthFragmentLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FourthFragmentLayoutBinding.inflate(inflater,container,false)

        binding.buttonMultyChoice.setOnClickListener{
            showMultyDialogFragment()}



        color = savedInstanceState?.getInt(KEY_COLOR) ?: Color.RED
        updateUi()

        setupMultipleChoiceDialogFragmentListener()
        return binding.root
    }

    private fun showMultyDialogFragment() {
        MultiDialogFragment.show(parentFragmentManager, this.color)
    }
    private fun setupMultipleChoiceDialogFragmentListener() {
        MultiDialogFragment.setupListener(parentFragmentManager, this) {
            this.color = it
            updateUi()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    private fun updateUi() {

        binding.colorView.setBackgroundColor(color)
    }

    companion object {
        @JvmStatic private val TAG = FragmentFour::class.java.simpleName
        @JvmStatic private val KEY_VOLUME = "KEY_VOLUME"
        @JvmStatic private val KEY_COLOR = "KEY_COLOR"
    }
}