package com.axfex.moneybalance.ui.category.dialog

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.transition.*

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppChoiseDialogFragment
import com.axfex.moneybalance.utils.addKeyboardEventListener
import kotlinx.android.synthetic.main.fragment_category_dialog.*
import kotlinx.android.synthetic.main.fragment_signin.*
import javax.inject.Inject

class CategoryDialogFragment : AppChoiseDialogFragment() {

    @Inject
    lateinit var viewModel: CategoryDialogViewModel

    @Inject
    lateinit var adapter: CategoryDialogAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editCategory.setOnClickListener {
            findNavController().navigate(R.id.action_to_categoryEditFragment)

        }
        categoryDialogList.adapter = adapter
        addKeyboardEventListener { isOpen ->

            if (isOpen) {
                val lp1 = LinearLayout.LayoutParams(categoryDialogList.layoutParams)
                val va = ValueAnimator.ofInt(lp1.height, 350)
                va.addUpdateListener { animation ->

                            lp1.height = animation.animatedValue as Int

                    categoryDialogList.layoutParams = lp1
                }
                va.duration=100
                va.start()



            } else {

                val lp1 = LinearLayout.LayoutParams(categoryDialogList.layoutParams)
                val va = ValueAnimator.ofInt(lp1.height, 750)
                va.addUpdateListener { animation ->

                    lp1.height = animation.animatedValue as Int

                    categoryDialogList.layoutParams = lp1
                }
                va.duration=100
                va.start()

            }
        }


    }
}
