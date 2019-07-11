package com.axfex.moneybalance.ui.category.dialog

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppChoiceDialogFragment
import com.axfex.moneybalance.utils.addKeyboardEventListener
import kotlinx.android.synthetic.main.fragment_category_dialog.*
import javax.inject.Inject

class CategoryDialogFragment : AppChoiceDialogFragment() {

    @Inject
    lateinit var viewModel: CategoryDialogViewModel

    @Inject
    lateinit var adapter: CategoryDialogAdapter

    val animationHandler = Handler()
    //    lateinit var animationTask: Runnable
    var animator: ValueAnimator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        settingsCategory.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_to_categoryListFragment)
        }


        addCategory.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_to_categoryListFragment)
        }

        searchCategory.setOnFocusChangeListener { _, _ ->
            val lp1 = LinearLayout.LayoutParams(categoryDialogRecycler.layoutParams)
            lp1.height = 0
            categoryDialogRecycler.layoutParams = lp1
        }

        categoryDialogRecycler.adapter = adapter
        addKeyboardEventListener { isOpen ->
            val endHeight: Int
            if (isOpen) {
                endHeight = 200
            } else {
                endHeight = 750
                searchCategory.clearFocus()
            }

            animationHandler.postDelayed({
                val lp = LinearLayout.LayoutParams(categoryDialogRecycler.layoutParams)
                animator?.cancel()
                animator = ValueAnimator.ofInt(lp.height, endHeight)
                animator?.let {
                    it.addUpdateListener { animation ->
                        lp.height = animation.animatedValue as Int
                        categoryDialogRecycler.layoutParams = lp
                    }
                    it.duration = 150
                    it.startDelay = 50L
                    it.start()
                }

            }, 50)
        }


    }

    override fun onPause() {
        super.onPause()
        animator?.cancel()
        animationHandler.removeCallbacksAndMessages(null)
    }
}
