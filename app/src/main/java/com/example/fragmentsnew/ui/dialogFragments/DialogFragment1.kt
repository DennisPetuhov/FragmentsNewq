package com.example.fragmentsnew.ui.dialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.fragmentsnew.R
import android.widget.Toast.makeText as makeText

class DialogFragment1:DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener { _, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_RESPONSE to which))
            // which  кнопка которая была нажата
        }
        return AlertDialog.Builder(requireContext())
            .setCancelable(true)
            .setIcon(R.mipmap.ic_launcher_round)
            .setTitle("title")
            .setMessage("default_alert_message")
            .setPositiveButton("R.string.action_yes", listener)
            .setNegativeButton("R.string.action_no", listener)
            .setNeutralButton("R.string.action_ignore", listener)
            .create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        Log.d(TAG, "Dialog dismissed")
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        showToast("dialog_cancelled")
    }

    private fun showToast(text:String) {
        return makeText(requireContext(),text, Toast.LENGTH_SHORT).show()


    }

    companion object {
        @JvmStatic val TAG = DialogFragment1::class.java.simpleName  // передаем вместо
        // контейнер айди, потмоу что диалог это отдельный фрагмент
        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey" // обеспечивает комуникацию между
        // диалогом и активити либо фрагментом откуда зарпускается. используется как ключ в бандле
        @JvmStatic val KEY_RESPONSE = "RESPONSE"
    }










}