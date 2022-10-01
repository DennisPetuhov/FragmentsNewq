package com.example.fragmentsnew.ui.dialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.LifecycleOwner
import com.example.fragmentsnew.R
import com.example.fragmentsnew.VolumeList

class SingleChoiceDialogFragment : DialogFragment() {
    private val volume: Int
        get() = requireArguments().getInt(ARG_VOLUME)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val volumeItems = VolumeList.CreateVolumeValues(volume) //выбранный элемент
        val volumeTextItem = volumeItems.values.map { getString(R.string.volume_description, it) }
            .toTypedArray()        // список который отражаем


        return AlertDialog.Builder(requireContext()).setTitle("volume setup")
            .setSingleChoiceItems(volumeTextItem, volumeItems.currentIndex) { _, which ->
                val chosenVolume = volumeItems.values[which]
                parentFragmentManager.setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(KEY_VOLUME_RESPONSE to chosenVolume)
                )
                dismiss()
            }
            .create()


    }



    companion object {
        fun show(manager: FragmentManager, volume: Int) {
            val dialogFragment = SingleChoiceDialogFragment()
            dialogFragment.arguments = bundleOf(ARG_VOLUME to volume) // передаем аргументы  внутри
            // для бандла внутри диалога
            dialogFragment.show(manager, TAG)

        }

        fun setUpListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (Int) -> Unit
        ) {
            manager.setFragmentResultListener(
                REQUEST_KEY,
                lifecycleOwner,
                FragmentResultListener { _, result ->
                    listener.invoke(result.getInt(KEY_VOLUME_RESPONSE))
                })
        }


        @JvmStatic
        val TAG = DialogFragment1::class.java.simpleName  // передаем вместо

        // контейнер айди, потмоу что диалог это отдельный фрагмент
        @JvmStatic
        val REQUEST_KEY = "$TAG:defaultRequestKey" // обеспечивает комуникацию между

        // диалогом и активити либо фрагментом откуда зарпускается. используется как ключ в бандле
        @JvmStatic
        val KEY_VOLUME_RESPONSE = "RESPONSE"

        @JvmStatic
        val ARG_VOLUME = "ARG_VOLUME"
    }


}


