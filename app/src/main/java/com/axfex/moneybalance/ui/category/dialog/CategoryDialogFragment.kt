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
import com.axfex.moneybalance.core.AppChoiseDialogFragment
import com.axfex.moneybalance.utils.addKeyboardEventListener
import kotlinx.android.synthetic.main.fragment_category_dialog.*
import javax.inject.Inject

class CategoryDialogFragment : AppChoiseDialogFragment() {

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
            findNavController().navigate(R.id.action_to_categoryEditFragment)

        }


        searchCategory.setOnFocusChangeListener { _, _ ->
            val lp1 = LinearLayout.LayoutParams(categoryDialogList.layoutParams)
            lp1.height = 0
            categoryDialogList.layoutParams = lp1
        }

        categoryDialogList.adapter = adapter
        addKeyboardEventListener { isOpen ->
            val endHeight: Int
            if (isOpen) {
                endHeight = 200
            } else {
                endHeight = 750
                searchCategory.clearFocus()
            }

            animationHandler.postDelayed({
                val lp = LinearLayout.LayoutParams(categoryDialogList.layoutParams)
                animator?.cancel()
                animator = ValueAnimator.ofInt(lp.height, endHeight)
                animator?.let {
                    it.addUpdateListener { animation ->
                        lp.height = animation.animatedValue as Int
                        categoryDialogList.layoutParams = lp
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
