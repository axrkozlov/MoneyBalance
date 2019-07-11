package com.axfex.moneybalance.core

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.axfex.moneybalance.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_ready.*

abstract class AppChoiceDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ChoiseDialog)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)


//        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)

        val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
        val width = (resources.displayMetrics.heightPixels * 0.90).toInt()
        dialog.window?.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        dialog.window?.setBackgroundDrawable(ColorDrawable(0));

        dialog.window?.setLayout(width, height)
        return dialog


    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }



}