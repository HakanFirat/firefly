package com.example.fireflyhakanfirat.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.fireflyhakanfirat.R
import java.lang.Exception

object DialogUtils {

    fun showMessage(context: Context?, message: String, asDialog: Boolean = false) {
        //  showDialog(context, message) {}

        if (asDialog)
            showDialog(context, message) {}
        else
            context.let {
                Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
            }
    }

    fun showDialog(context: Context?, message: String?,
                   positiveButtonPressed: () -> Unit) {
        try {
            context?.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage(message)
                builder.setCancelable(false)

                builder.setPositiveButton(R.string.ok) { _, _ ->
                    positiveButtonPressed()
                }

                builder.show()
            }
        }catch (e: Exception){
            positiveButtonPressed()
        }
    }

    fun showTwoButtonsDialog(
        context: Context?,
        message: String,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveListener: DialogInterface.OnClickListener,
        negativeListener: DialogInterface.OnClickListener
    ) {
        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(message)
            builder.setCancelable(false)

            builder.setPositiveButton(positiveButtonText, positiveListener)
            builder.setNegativeButton(negativeButtonText, negativeListener)

            builder.show()
        }
    }
}